package hello.advanced.trace.template;

import hello.advanced.trace.template.code.AbstractTemplate;
import hello.advanced.trace.template.code.SubclassLogic1;
import hello.advanced.trace.template.code.SubclassLogic2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class TemplateMethodTest {

  @Test
  void templateMethodV0() {
    logic1();
    logic2();
  }

  private void logic1() {
    long startTime = System.currentTimeMillis();
    // 비즈니스 로직 실행
    log.info("비즈니스 로직1 실행");
    // 비즈니스 로직 종료
    long endTime = System.currentTimeMillis();
    long resultTime = endTime - startTime;
    log.info("resultTime={}", resultTime);
  }

  private void logic2() {
    long startTime = System.currentTimeMillis();
    // 비즈니스 로직 실행
    log.info("비즈니스 로직2 실행");
    // 비즈니스 로직 종료
    long endTime = System.currentTimeMillis();
    long resultTime = endTime - startTime;
    log.info("resultTime={}", resultTime);
  }

  /*
   * 템플릿 메서드 패턴 적용
   * */
  @Test
  void templateMethodV1() {
    AbstractTemplate template1 = new SubclassLogic1();
    AbstractTemplate template2 = new SubclassLogic2();
    template1.execute();
    template2.execute();
  }

  @Test
  void templateMethodV2() {
    AbstractTemplate abstractTemplate1 = new AbstractTemplate() {
      @Override
      protected void call() {
        log.info("logic1");
      }
    };
    AbstractTemplate abstractTemplate2 = new AbstractTemplate() {
      @Override
      protected void call() {
        log.info("logic2");
      }
    };
    abstractTemplate1.execute();
    log.info("class name1 ={}", abstractTemplate1.getClass());
    abstractTemplate2.execute();
    log.info("class name2 ={}", abstractTemplate2.getClass());
  }


}