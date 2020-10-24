package com.srtp.taxi;

import com.srtp.taxi.algorithm.analyse;
import com.srtp.taxi.alphacar.Graph.ListAD;
import com.srtp.taxi.alphacar.bean.DAU;
import com.srtp.taxi.service.DispatchService;
import com.srtp.taxi.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableScheduling
public class TaxiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaxiApplication.class, args);


//		DAU dau1=new DAU(123,123,-1);
//		DAU dau2=new DAU(1234,1233,-1);
//		DAU dau3=new DAU(1235,1234,-1);
//		DAU dau4=new DAU(1236,1235,-1);
//		DAU dau5=new DAU(1237,1236,4);
//
//		DAU[] daus=new DAU[5];
//		daus[0]=dau1;
//		daus[1]=dau2;
//		daus[2]=dau3;
//		daus[3]=dau4;
//		daus[4]=dau5;
//
//		DAU[][] daulist=new DAU[4][2];
//		daulist[0][0]=dau1;
//		daulist[0][1]=dau5;
//		daulist[1][0]=dau2;
//		daulist[1][1]=dau5;
//		daulist[2][0]=dau3;
//		daulist[2][1]=dau5;
//		daulist[3][0]=dau3;
//		daulist[3][1]=dau5;
//
//		int[] weight=new int[4];
//
//		weight[0]=1;
//		weight[1]=2;
//		weight[2]=3;
//		weight[3]=4;
//
//
//
//		ListAD test=new ListAD(daus, daulist,weight);
	}


}
