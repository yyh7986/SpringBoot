package com.himedia.g14.service;

import com.himedia.g14.dao.ICartDao;
import com.himedia.g14.dao.IOrderDao;
import com.himedia.g14.dto.CartVO;
import com.himedia.g14.dto.OrderVO;
import com.oracle.wls.shaded.org.apache.xpath.operations.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    IOrderDao odao;
    @Autowired
    ICartDao cdao;

    public int insertOrder(String[] cseqs, String userid) {

        // 1. Order 테이블에 레코드 추가
        odao.insertOrders(userid);

        // 2. 방금 추가한 레코드의 oseq 조회
        int oseq = odao.lookupMaxOseq();

        // 3. cseq 들로 카트조회 & order_detail 추가
        for (String cseq : cseqs) {
            CartVO cvo = odao.getCart(Integer.parseInt(cseq));
//            OrderVO ovo = new OrderVO();
//            ovo.setUserid(userid);
//            ovo.setOseq(oseq);
//            ovo.setPseq(cvo.getPseq());
//            ovo.setQuantity(cvo.getQuantity());
//            odao.insertOrderDetail(ovo);
            odao.insertOrderDetail(oseq, cvo);

            // 4. 카트에서 주문한 목록 삭제
            cdao.deleteCart(Integer.parseInt(cseq));
        }

        // 5. oseq 주문번호 리턴
        return oseq;
    }

    public HashMap<String, Object> getOrderByOseq(int oseq) {
        HashMap<String, Object> result = new HashMap<>();
        List<OrderVO> list = odao.getOrderByOseq(oseq);
        result.put("orderList", list);
        int totalPrice = 0;
        for (OrderVO ovo : list) {
            totalPrice += ovo.getPrice2() * ovo.getQuantity();
        }
        result.put("totalPrice", totalPrice);
        return result;
    }

    public int insertOrderOne(int pseq, int quantity, String userid) {
        odao.insertOrders(userid);
        int oseq = odao.lookupMaxOseq();
        odao.insertOrderDetailOne(oseq, pseq, quantity);
        return oseq;
    }

    public List<OrderVO> myPageList(String userid) {

        // 최종 mypage.jsp에 전달될 리스트
        ArrayList<OrderVO> finalList = new ArrayList<>();

        // 아이디로 주문 번호(oseq)들을 조회
        List<Integer> oseqList = odao.getOseqListIng(userid);

        // 조회된 주문번호별로 최종 리스트를 구성
        for (Integer oseq : oseqList) {
            List<OrderVO> orderListByOseq = odao.getOrderByOseq(oseq);
            OrderVO temp = (OrderVO) orderListByOseq.get(0);
            temp.setPname(temp.getPname() + " 포함 " + orderListByOseq.size() + "건");
            int totalPrice = 0;
            for (OrderVO ovo : orderListByOseq) {
                totalPrice += ovo.getPrice2() * ovo.getQuantity();
            }
            temp.setPrice2(totalPrice);
            finalList.add(temp);
        }
        return finalList;
    }

    public Object orderAllList(String userid) {

        // 최종 mypage.jsp에 전달될 리스트
        ArrayList<OrderVO> finalList = new ArrayList<>();

        // 아이디로 주문 번호(oseq)들을 조회
        List<Integer> oseqList = odao.getOseqListAll(userid);

        // 조회된 주문번호별로 최종 리스트를 구성
        for (Integer oseq : oseqList) {
            List<OrderVO> orderListByOseq = odao.getOrderByOseq(oseq);
            OrderVO temp = (OrderVO) orderListByOseq.get(0);
            temp.setPname(temp.getPname() + " 포함 " + orderListByOseq.size() + "건");
            int totalPrice = 0;
            for (OrderVO ovo : orderListByOseq) {
                totalPrice += ovo.getPrice2() * ovo.getQuantity();
            }
            temp.setPrice2(totalPrice);
            finalList.add(temp);
        }
        return finalList;

    }
}
