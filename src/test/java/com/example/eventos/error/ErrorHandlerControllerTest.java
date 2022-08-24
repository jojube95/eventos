package com.example.eventos.error;

import com.example.utilities.TestUtilities;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.servlet.RequestDispatcher;

import static com.example.utilities.TestUtilities.processContent;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ErrorHandlerController.class)
public class ErrorHandlerControllerTest {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username="usuario",roles={"USUARIO"})
    void testErrorWithException() throws Exception {
        String expectedResponse = TestUtilities.getContent("src/test/resources/response.html/errorPageException.html");

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.get("/error")
                .requestAttr(RequestDispatcher.ERROR_STATUS_CODE, 500)
                .requestAttr(RequestDispatcher.ERROR_EXCEPTION, new Exception("Test exception message"));

        String resultContent = this.mockMvc.perform(mockRequest).andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        resultContent = processContent(resultContent);

        assertThat(resultContent, CoreMatchers.containsString(expectedResponse));
    }

    @Test
    @WithMockUser(username="usuario",roles={"USUARIO"})
    void testErrorWithoutException404() throws Exception {
        String expectedResponse = TestUtilities.getContent("src/test/resources/response.html/error404.html");

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.get("/error")
                .requestAttr(RequestDispatcher.ERROR_STATUS_CODE, 404);

        String resultContent = this.mockMvc.perform(mockRequest).andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        resultContent = processContent(resultContent);

        assertThat(resultContent, CoreMatchers.containsString(expectedResponse));
    }

    @Test
    @WithMockUser(username="usuario",roles={"USUARIO"})
    void testErrorWithoutException403() throws Exception {
        String expectedResponse = TestUtilities.getContent("src/test/resources/response.html/error403.html");

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.get("/error")
                .requestAttr(RequestDispatcher.ERROR_STATUS_CODE, 403);

        String resultContent = this.mockMvc.perform(mockRequest).andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        resultContent = processContent(resultContent);

        assertThat(resultContent, CoreMatchers.containsString(expectedResponse));
    }

    @Test
    @WithMockUser(username="usuario",roles={"USUARIO"})
    void testErrorWithoutException405() throws Exception {
        String expectedResponse = TestUtilities.getContent("src/test/resources/response.html/error405.html");

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.get("/error")
                .requestAttr(RequestDispatcher.ERROR_STATUS_CODE, 405);

        String resultContent = this.mockMvc.perform(mockRequest).andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        resultContent = processContent(resultContent);

        assertThat(resultContent, CoreMatchers.containsString(expectedResponse));
    }

}
