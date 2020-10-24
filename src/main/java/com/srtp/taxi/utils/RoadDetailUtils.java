package com.srtp.taxi.utils;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

public class RoadDetailUtils {
    private static JsonRootBean getRoadDetail(String url, HttpMethod method, MultiValueMap<String,String> params){
        RestTemplate template =new RestTemplate();
        ResponseEntity<JsonRootBean> responseEntity=template.getForEntity(url,
                JsonRootBean.class);
        return  responseEntity.getBody();
    }

    public static ArrayList<double []> getDistance(double startLng,double startLat,double[] endLng,double[] endLat){
        JsonRootBean js;
        String str="https://apis.map.qq.com/ws/distance/v1/matrix/?mode=driving&from=";
        str=str+startLat+","+startLng+"&to=";


        for(int i=0;i<endLng.length-1;i++){
            str=str+endLat[i]+","+endLng[i]+";";
        }
        str=str+endLat[endLat.length-1]+","+endLng[endLng.length-1]+"&key=MMBBZ-BIALF-NTNJV-JJ74D-APHP2-UHFSM";
        while (true){
            js=getRoadDetail(str,HttpMethod.GET,new LinkedMultiValueMap<>());
            if(js.getResult()!=null){
                break;
            }
        }

        ArrayList<double []> disList= new ArrayList<>();
        double [] t;
        for(int j=0;j<js.getResult().getRows().get(0).getElements().size();j++){
            t=new double[5];
            t[0]=startLng;
            t[1]=startLat;
            t[2]=endLng[j];
            t[3]=endLat[j];
            t[4]=js.getResult().getRows().get(0).getElements().get(j).getDistance();
            disList.add(t);
        }
        return disList;
    }

    public static ArrayList<double []> getTime(double startLng,double startLat,double[] endLng,double[] endLat){
        JsonRootBean js;
        String str="https://apis.map.qq.com/ws/distance/v1/matrix/?mode=driving&from=";
        str=str+startLat+","+startLng+"&to=";


        for(int i=0;i<endLng.length-1;i++){
            str=str+endLat[i]+","+endLng[i]+";";
        }
        str=str+endLat[endLat.length-1]+","+endLng[endLng.length-1]+"&key=MMBBZ-BIALF-NTNJV-JJ74D-APHP2-UHFSM";
        while (true){
            js=getRoadDetail(str,HttpMethod.GET,new LinkedMultiValueMap<>());
            if(js.getResult()!=null){
                break;
            }
        }

        ArrayList<double []> timeList= new ArrayList<>();
        double [] t;
        for(int j=0;j<js.getResult().getRows().get(0).getElements().size();j++){
            t=new double[5];
            t[0]=startLng;
            t[1]=startLat;
            t[2]=endLng[j];
            t[3]=endLat[j];
            t[4]=js.getResult().getRows().get(0).getElements().get(j).getDuration();
            timeList.add(t);
        }
        return timeList;
    }

    public static int getTwoDistance(double startLng,double startLat,double endLng,double endLat,ArrayList<double []> disList){
        int size=disList.size();
        for(int a=0;a<size;a++){
            if(disList.get(a)[0]==startLng&&disList.get(a)[1]==startLat&&disList.get(a)[2]==endLng&&disList.get(a)[3]==endLat){
                return (int)disList.get(a)[4];
            }
        }
        return -1;
    }
    public static int getTwoTime(double startLng,double startLat,double endLng,double endLat,ArrayList<double []> timeList){
        int size=timeList.size();
        for(int a=0;a<size;a++){
            if(timeList.get(a)[0]==startLng&&timeList.get(a)[1]==startLat&&timeList.get(a)[2]==endLng&&timeList.get(a)[3]==endLat){
                return (int)timeList.get(a)[4];
            }
        }
        return -1;

    }


}

