<<<<<<<< HEAD:order-service/src/main/java/com/growmighty/lectures/firstday/order/application/dto/OrderConsistencyView.java
package com.growmighty.lectures.firstday.order.application.dto;
|||||||| parent of f914e54 (Step4: 도메인별 DDD 4계층 분리 + DIP 적용):src/main/java/com/growmighty/lectures/firstday/tangledmonolith/order/OrderConsistencyView.java
package com.growmighty.lectures.firstday.tangledmonolith.order;
========
package com.growmighty.lectures.firstday.tangledmonolith.order.application.dto;
>>>>>>>> f914e54 (Step4: 도메인별 DDD 4계층 분리 + DIP 적용):src/main/java/com/growmighty/lectures/firstday/tangledmonolith/order/application/dto/OrderConsistencyView.java

import java.math.BigDecimal;

public record OrderConsistencyView(
        Long orderId,
        BigDecimal storedTotal,
        BigDecimal recalculatedTotal,
        boolean consistent
) {
}
