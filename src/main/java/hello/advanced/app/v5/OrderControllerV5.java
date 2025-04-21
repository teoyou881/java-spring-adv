package hello.advanced.app.v5;

import hello.advanced.trace.callback.TraceTemplate;
import hello.advanced.trace.logtrace.LogTrace;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderControllerV5 {

  private final OrderServiceV5 orderService;
  private final TraceTemplate template;

  // TraceTemplate가 LogTrace가 필요하다.
  public OrderControllerV5(OrderServiceV5 orderService, LogTrace trace) {
    this.orderService = orderService;
    this.template = new TraceTemplate(trace);
  }

  @GetMapping("/v5/request")
  public String request(String itemId) {

    // 명시적 타입 지정
    return template.<String>execute(
        "OrderController.request()", () -> {
          orderService.orderItem(itemId);
          return "ok";
        });
  }
}