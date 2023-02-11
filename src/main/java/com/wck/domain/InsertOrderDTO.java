package com.wck.domain;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class InsertOrderDTO {

	@NotNull
	private int beforeprice;
	@NotNull
	private int afterprice;
	@NotEmpty
	private String rcpt_name;
	@NotEmpty
	private String pmCode;
	@NotEmpty
	private String pmCompany;
	@NotEmpty(message = "핸드폰 번호를 입력해주세요")
	private String phone;
	@NotEmpty(message = "주소를 입력해주세요")
	private String zipcode;
	@NotEmpty(message = "주소를 입력해주세요")
	private String address1;

	@NotEmpty
	private String[] psids;
	@NotNull
	private int[] counts;

	private String address2;
	private String gl_radio_dlvr_msg;
	private String frontDoorSelectMessage;
	private String voucherCode;
	private int useMileage;
	private String mId;

	private String status = "결제 완료";
	private String tel;
	private String email;

	public OrderVO toOrderVO() {
		OrderVO order = new OrderVO();
		
		String pattern = "yyMMdd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, new Locale("ko", "KR"));
        String date = simpleDateFormat.format(new Date());
        long unixTime = System.currentTimeMillis() / 1000;
        String oid = date + "P" + String.valueOf(unixTime);
        
        String memo = "";
        if(!gl_radio_dlvr_msg.equals("") & !frontDoorSelectMessage.equals("")) {
        	memo = gl_radio_dlvr_msg + "<br>" + frontDoorSelectMessage;
        } else if(!gl_radio_dlvr_msg.equals("")) {
			memo = gl_radio_dlvr_msg;
		} else if(!frontDoorSelectMessage.equals("")) {
			memo = frontDoorSelectMessage;
		}
        
        order.setOid(oid);
        order.setOzipcode(zipcode);
        order.setOaddress1(address1);
        order.setOaddress2(address2);
        order.setOreceiver(rcpt_name);
        order.setOphone(phone);
        order.setOtel(tel);
        order.setOmemo(memo);
        order.setOemail(email);
        order.setOusedMileage(useMileage);
        order.setObeforePrice(beforeprice);
        order.setOafterPrice(afterprice);
        order.setOstatus(status);
        order.setCpid(voucherCode);
        order.setPmCode(pmCode);
        order.setPmCompany(pmCompany);
        order.setMid(mId);        
        if(pmCompany.equals("credit")) {
        	order.setPmMethod("0");
        } else {
        	order.setPmMethod("1");
        }
        
        List<OrderProductVO> prods = new LinkedList<OrderProductVO>();
        for(int i=0; i<psids.length; i++) {
        	OrderProductVO prod = new OrderProductVO();
        	prod.setPsId(psids[i]);
        	prod.setQuantity(counts[i]);
        	prods.add(prod);
        }
        order.setOrderProducts(prods);
        
		return order;
	}
}
