package com.fx.xzt.match.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
* @ClassName: MatchRuleController 
* @Description: 比赛规则操作类 
* @author jcwang
* @date 2017年8月21日 下午2:07:44 
*
 */
@Controller
@RequestMapping("/competition")
public class MatchRuleController {

	 /** 
     * 比赛规则信息
     */  
    @RequestMapping("/rule/info")  
    public String toRule(){ 
         return "match/rules";
    } 
	
}
