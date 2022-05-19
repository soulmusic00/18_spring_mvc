package com.spring.mvc.dataTransfer.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.mvc.dataTransfer.dto.MemberDto;
import com.spring.mvc.dataTransfer.dto.OrderDto;
import com.spring.mvc.dataTransfer.dto.ProductDto;

@Repository		// 해당 클래스가 dao임을 스프링 빈에 등록한다.
public class DataTransferDao {

	@Autowired
	private SqlSession sqlSession;
	
	/*
	 * 
	 * # Mapper To Dao
	 * 
	 *  - 한개의 데이터를 조회할 경우       .selectOne() 메서드를 사용한다.
	 *  - 한개 이상의 데이터를 조회할 경우  .selectList() 메서드를 사용하며 반환데이터는 List로 처리할 수 있다.
	 *      주의) Mapper 파일에서 반환타입(resultType)이 List가 아니고
	 *            selectList() 메서드로 List를 처리한다.
	 *  
	 *  - insert쿼리를 사용할 경우 .insert() 메서드를 사용한다.
	 *  - update쿼리를 사용할 경우 .update() 메서드를 사용한다.
	 *  - delete쿼리를 사용할 경우 .delete() 메서드를 사용한다.
	 *  
	 * */
	
	public void selectData1() {
		
		System.out.println("\n selectData1 \n");
	
		int result = sqlSession.selectOne("dataTransfer.select1");
		System.out.println("selectData1 : " + result);
		
	}
	
	public void selectData2() {
		
		System.out.println("\n selectData2 \n");
		
		List<ProductDto> productList = sqlSession.selectList("dataTransfer.select2");
		for (ProductDto productDto : productList) {
			System.out.println(productDto);
			System.out.println();
		}
		
	}
	
	public void selectData3() {
		
		System.out.println("\n selectData3 \n");
		
		List<Map<String,Object>> productList = sqlSession.selectList("dataTransfer.select3");
		for (Map<String,Object> productMap : productList) {
			System.out.println(productMap);
			System.out.println();
		}
		
	}
	
	public void selectData4() {
		
		System.out.println("\n selectData4 \n");
		List<ProductDto> productList = sqlSession.selectList("dataTransfer.select4");
		for (ProductDto productDto : productList) {
			System.out.println(productDto);
			System.out.println();
		}
		
	}
	
	public void selectData5() {
		
		System.out.println("\n selectData5 \n");
		
		List<Map<String,Object>> productList = sqlSession.selectList("dataTransfer.select5");
		for (Map<String,Object> productMap : productList) {
			System.out.println(productMap);
			System.out.println();
		}
		
	}
	
	/*
	 * 
	 * # Dao To Mapper
	 * 
	 *  - 2개 이상의 파라미터를 Mapper로 전달할 수 없고 오직 1개의 파라미터만 전송이 가능하다.
	 *  - 2개 이상의 데이터는 Dto , Map형식으로 전송한다.
	 *  - 전송되는 복수의 데이터가 Dto의 멤버로 포함되어 있으면 일반적으로 Dto 전송 방식을 사용하고
	 *    전송되는 복수의 데이터가 Dto의 멤버에 포함되어 있지 않은 경우 Map 전송 방식을 사용한다.
	 * 
	 * */
	
	// 1) Dao To Mapper 단일 데이터 전송 예시
	
	public void memberInfo(String memberId) {
		
		MemberDto memberDto = sqlSession.selectOne("dataTransfer.memberInfo" , memberId);
		System.out.println(memberDto);
		System.out.println();
		
	}
	
	public void productInfo(String productCode) {
		
		ProductDto productDto = sqlSession.selectOne("dataTransfer.productInfo" , productCode);
		System.out.println(productDto);
		System.out.println();
		
	}
	
	public void orderInfo(String orderCode) {
		
		OrderDto orderDto = sqlSession.selectOne("dataTransfer.orderInfo" , orderCode);
		System.out.println(orderDto);
		System.out.println();
		
	}
	
	
	// 2) Dao To Mapper DTO 전송 예시
	public void insertProduct(ProductDto productDto) {
		sqlSession.insert("dataTransfer.addProduct" , productDto);
	}
	
	
	// 3) Dao To Mapper Map 전송 예시
	public void selectData1(Map<String,Object> orderMap) {
		
		System.out.println("\n selectData1 \n");
		
		List<OrderDto> orderList = sqlSession.selectList("dataTransfer.selectData1" , orderMap);
		for (OrderDto orderDto : orderList) {
			System.out.println(orderDto);
			System.out.println();
		}
		
		
	}
	
	public void selectData2(Map<String,Object> orderMap) {
		
		System.out.println("\n selectData2 \n");
		
		List<Map<String,Object>> orderList = sqlSession.selectList("dataTransfer.selectData2" , orderMap);
		for (Map<String, Object> order : orderList) {
			System.out.println(order);
			System.out.println();
		}
		
	}
	
	public void selectData3(Map<String,Object> orderMap) {
		
		System.out.println("\n selectData3 \n");
		
		int totalOrderGoodsQty = sqlSession.selectOne("dataTransfer.selectData3" , orderMap);
		System.out.println("2020년도 총 주문 개수 : " + totalOrderGoodsQty);
		System.out.println();
		
	}
	
}