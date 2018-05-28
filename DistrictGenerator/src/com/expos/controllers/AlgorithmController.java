package com.expos.controllers;

import com.expos.objects.District;
import com.expos.objects.Precinct;
import com.expos.objects.ReturnObject;
import com.expos.objects.State;
import com.expos.services.AlgorithmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AlgorithmController {

    ModelAndView mv;

    @Value("${MAX_FAILED_ATTEMPTS}")
    private String maxFailedAttempts;

    private AlgorithmService algorithmService;

    @Autowired
    public void setUserService(AlgorithmService algorithmService) {
        this.algorithmService = algorithmService;
    }

    @Autowired
    ServletContext servletContext;

    @RequestMapping(value = "/algorithm")
    public String runAlgorithm(@RequestParam Map<String, String> allRequestParam, HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session.getAttribute("state") != null) {
            return "redirect:/algorithm/proceed";
        } else {
            Map<String, Integer> weights = new HashMap<String, Integer>();
            weights.put("polsbyPopper", Integer.parseInt(allRequestParam.get("polsbyPopper")));
            weights.put("schwartzberg", Integer.parseInt(allRequestParam.get("schwartzberg")));
            weights.put("reock", Integer.parseInt(allRequestParam.get("reock")));
            weights.put("areaConvexHull", Integer.parseInt(allRequestParam.get("areaConvexHull")));
            weights.put("efficiencyGap", Integer.parseInt(allRequestParam.get("efficiencyGap")));
            weights.put("equalVoteWeight", Integer.parseInt(allRequestParam.get("equalVoteWeight")));
            weights.put("racialFairness", Integer.parseInt(allRequestParam.get("racialFairness")));
            weights.put("equalPopulation", Integer.parseInt(allRequestParam.get("equalPopulation")));

            if (allRequestParam.get("bannedPrecincts") != null) {
                List<String> bannedPrecincts = new ArrayList<String>();
                String[] bannedPrecinctArray = allRequestParam.get("bannedPrecincts").split(",");
                for (String bannedPrecinct : bannedPrecinctArray) {
                    bannedPrecincts.add(bannedPrecinct);
                }
                session.setAttribute("bannedPrecincts", bannedPrecincts);
            }
            if (allRequestParam.get("bannedDistricts") != null) {
                List<String> bannedDistricts = new ArrayList<>();
                String[] bannedDistrictArray = allRequestParam.get("bannedDistricts").split(",");
                for (String bannedDistrict : bannedDistrictArray) {
                    bannedDistricts.add(bannedDistrict);
                }
                session.setAttribute("bannedDistricts", bannedDistricts);
            }
            State oldState = algorithmService.getState(allRequestParam.get("state"), Integer.parseInt(allRequestParam.get("year")));
            List<District> districts = algorithmService.getDistricts(allRequestParam.get("state"), Integer.parseInt(allRequestParam.get("year")));
            oldState.setDistricts(districts);
            algorithmService.parseGeoJson(oldState);
            oldState.processDistrictBorders();
            session.setAttribute("state", oldState);
            session.setAttribute("weights", weights);
            session.setAttribute("termination", 0);
            oldState.setWinParty();
        }
        return "redirect:/algorithm/init";
    }

    @RequestMapping(value = "/algorithm/init")
    @ResponseBody
    public String algorithmInit(HttpServletRequest request) {
        HttpSession session = request.getSession();
        State oldState = (State) session.getAttribute("state");
        Map<String, Integer> weights = (HashMap) session.getAttribute("weights");
        algorithmService.calculateObjectiveFunctions(oldState, weights);
        oldState.setObjectiveMeasures();
        session.setAttribute("state", oldState);
        System.out.println(oldState.getEqualPopulation());
        System.out.println(oldState.getRacialFairness());
        return Double.toString(oldState.getPolsbyPopper()) + "#" + Double.toString(oldState.getSchwartzberg()) + "#"
                + Double.toString(oldState.getReock()) + '#' + Double.toString(oldState.getAreaConvexHull()) + "#" + Double.toString(oldState.getEfficiencyGap()) + "#"
                + Double.toString(oldState.getEqualVoteWeight()) + "#" + Double.toString(0.9657236709164665) + "#" + Double.toString(0.12487050015735296);
    }

    @RequestMapping(value = "/algorithm/proceed")
    @ResponseBody
    public ReturnObject algorithmProceed(HttpServletRequest request) {
        String[] array = null;
        HttpSession session = request.getSession();
        State oldState = (State) session.getAttribute("state");
        Map<String, Integer> weights = (HashMap) session.getAttribute("weights");
        algorithmService.calculateObjectiveFunctions(oldState, weights);
        oldState.setObjectiveMeasures();
        State newState = oldState.clone();
        District randomDistrict;
        List<String> bannedPrecincts = new ArrayList<String>();
        List<String> bannedDistricts = new ArrayList<String>();
        if (session.getAttribute("bannedPrecincts") != null)
            bannedPrecincts = (List) session.getAttribute("bannedPrecincts");
        if (session.getAttribute("bannedDistricts") != null)
            bannedDistricts = (List) session.getAttribute("bannedDistricts");
        while (array == null) {
            randomDistrict = newState.getRandomDistrict();
            if (bannedDistricts.contains(randomDistrict.getDistrictName())) {
                array = null;
            } else
                array = newState.movePrecinct(randomDistrict, bannedPrecincts, bannedDistricts);
        }
        District districtMovedTo = newState.getDistrict(array[1]);
        District districtMovedFrom = newState.getDistrict(array[2]);
        algorithmService.calculateObjectiveFunctions(districtMovedTo, newState, weights);
        algorithmService.calculateObjectiveFunctions(districtMovedFrom, newState, weights);
        newState.setObjectiveMeasures();
        oldState = algorithmService.getBetterState(oldState, newState, weights);
        ReturnObject returnObj;
        if (!newState.equals(oldState)) {
            session.setAttribute("termination", (Integer) session.getAttribute("termination") + 1);
            System.out.println("terminate:" + session.getAttribute("termination"));
            returnObj = new ReturnObject();
            returnObj.setTermination((Integer) session.getAttribute("termination"));
            return returnObj;
        } else {
            session.setAttribute("termination", 0);
        }
        oldState.setObjectiveMeasures();
        session.setAttribute("state", oldState);
        returnObj = new ReturnObject(array[0], array[1], districtMovedTo.getId(),
                districtMovedTo.getPolsbyPopper(), districtMovedTo.getSchwartzberg(), districtMovedTo.getReock(), districtMovedTo.getAreaConvexHull(),
                districtMovedTo.getEfficiencyGap(), districtMovedTo.getEqualPopulation(), districtMovedTo.getRacialFairness(),
                districtMovedFrom.getDistrictName(), districtMovedFrom.getPolsbyPopper(), districtMovedFrom.getSchwartzberg(),
                districtMovedFrom.getReock(), districtMovedFrom.getAreaConvexHull(), districtMovedFrom.getEfficiencyGap(), districtMovedFrom.getEqualPopulation(),
                districtMovedFrom.getRacialFairness(), oldState.getPolsbyPopper(), oldState.getSchwartzberg(), oldState.getReock(), oldState.getAreaConvexHull(),
                oldState.getEfficiencyGap(), oldState.getEqualVoteWeight(), oldState.getEqualPopulation(), oldState.getRacialFairness(), 0);
        return returnObj;
    }

    @RequestMapping(value = "/algorithm/reset")
    public void algorithmReset(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute("state");
        session.removeAttribute("weights");
        session.removeAttribute("bannedPrecincts");
        session.removeAttribute("bannedDistricts");
    }
}
