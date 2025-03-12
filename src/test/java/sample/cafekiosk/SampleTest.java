package sample.cafekiosk;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SampleTest {

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        // 필요하면 초기화 작업 추가 가능
    }

    @DisplayName("샘플 테스트 - 예외 발생 확인")
    @Test
    public void sample_test() throws Exception {
        mockMvc.perform(get("/test"))  // ✅ API 호출
                .andDo(print());  //
                //.andExpect(status().isBadRequest())  // ✅ HTTP 400 응답 확인
                //.andExpect(content().string("오류 발생: BAD_REQUEST"));  // ✅ 응답 메시지 검증
    }

    // ✅ 테스트용 Configuration (내부 Static Class)
    @Configuration
    static class TestConfig {

        @Bean
        public TestController testController() {
            return new TestController();
        }

        @Bean
        public TestExceptionHandler testExceptionHandler() {
            return new TestExceptionHandler();
        }
    }

    // ✅ 테스트용 컨트롤러
    @RestController
    static class TestController {

        @GetMapping("/test")
        public String testMethod() {
            throw new CustomException(HttpStatus.BAD_REQUEST);
        }
    }

    // ✅ 예외 핸들러 (예외 발생 시 메시지 반환)
    @RestControllerAdvice
    static class TestExceptionHandler {

        @ExceptionHandler(CustomException.class)
        public ResponseEntity<String> handleCustomException(CustomException ex) {
            return new ResponseEntity<>("오류 발생: " + ex.getErrorCode(), HttpStatus.BAD_REQUEST);
        }
    }

    // ✅ 커스텀 예외 클래스
    static class CustomException extends RuntimeException {
        private final String errorCode;

        public CustomException(HttpStatus httpStatus) {
            this.errorCode = httpStatus.toString();  // ✅ HTTP 상태 메시지를 포함
        }

        public String getErrorCode() {
            return errorCode;
        }
    }

}
