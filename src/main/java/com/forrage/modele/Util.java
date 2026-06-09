package com.forrage.modele;

import java.util.*;

import org.springframework.stereotype.Component;

@Component
public class Util {
    private Map<String, Integer> map;

    /*Constructeur*/
    public Util(){
        this.map = new HashMap<>();
        this.map.put("DEC", 3);
        this.map.put("DFC",5);
    }

    /*Setters*/
    public void setMap(Map<String, Integer> map) {
        this.map = map;
    }

    /*Getters*/
    public Map<String, Integer> getMap() {
        return map;
    }

    public int getIdStatutBySigle(String sigle) {
        return this.map.get(sigle);
    }
}
