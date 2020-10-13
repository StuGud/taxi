package com.srtp.taxi.utils;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class RoadDetailUtils {
    private static JsonRootBean getRoadDetail(String url, HttpMethod method, MultiValueMap<String,String> params){
        RestTemplate template =new RestTemplate();
        ResponseEntity<JsonRootBean> responseEntity=template.getForEntity(url,
                JsonRootBean.class);
        return  responseEntity.getBody();
    }

    public static int getDistance(double startLng,double startLat,double endLng,double endLat){
        JsonRootBean js=getRoadDetail("https://apis.map.qq.com/ws/distance/v1/matrix/?mode=driving&from="+startLat+","+startLng+"&to="+endLat+","+endLng+"&key=MMBBZ-BIALF-NTNJV-JJ74D-APHP2-UHFSM"
        ,HttpMethod.GET,new LinkedMultiValueMap<>());
        return js.getResult().getRows().get(0).getElements().get(0).getDistance();
    }

    public static int getTime(double startLng,double startLat,double endLng,double endLat){
        JsonRootBean js=getRoadDetail("https://apis.map.qq.com/ws/distance/v1/matrix/?mode=driving&from="+startLat+","+startLng+"&to="+endLat+","+endLng+"&key=MMBBZ-BIALF-NTNJV-JJ74D-APHP2-UHFSM"
                ,HttpMethod.GET,new LinkedMultiValueMap<>());
        return js.getResult().getRows().get(0).getElements().get(0).getDuration();
    }


}

