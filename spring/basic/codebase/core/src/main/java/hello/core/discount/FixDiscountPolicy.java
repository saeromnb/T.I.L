package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;

public class FixDiscountPolicy implements DiscountPolicy {

    private int discountFixAmount = 1000; //1000원할인

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {  // Enum 타입은 `==` 비교
            return discountFixAmount;
        } else {
            return 0;
        }

    }
}
