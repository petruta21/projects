package com.example.product.productlist;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.File;
import java.nio.file.Files;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


@SpringBootTest
@TestPropertySource(
        locations = "classpath:application-integration.properties")
@AutoConfigureMockMvc
public class ProductListApplicationTest {

    @Test
    void contextLoads() { //verify if the application is able to load Spring context successfully or not.
    }

    @Autowired
    private MockMvc mockMvc;

    @Test
    @SqlGroup({
            @Sql(value = "classpath:sql/reset.sql", executionPhase = BEFORE_TEST_METHOD),
            @Sql(value = "classpath:sql/user-data.sql", executionPhase = BEFORE_TEST_METHOD)
    })
    void should_create_one_user() throws Exception {
        final File jsonFile = new ClassPathResource("json/new_user.json").getFile();
        final String userToCreate = Files.readString(jsonFile.toPath());

        ResultActions resultActions = this.mockMvc.perform(post("/registration/user")
                        .contentType(APPLICATION_JSON)
                        .content(userToCreate))
                .andDo(print());

        Long newId = Long.parseLong(resultActions.andReturn().getResponse().getContentAsString());

        this.mockMvc.perform(MockMvcRequestBuilders.get("/registration/{id}", newId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Marta"))
                .andExpect(jsonPath("$.email").value("Marta@tut.by"))
                .andExpect(jsonPath("$.password").value("marta_muwi"))
                .andExpect(jsonPath("$.id").value(newId));
    }

    @Test
    @SqlGroup({
            @Sql(value = "classpath:sql/reset.sql", executionPhase = BEFORE_TEST_METHOD),
            @Sql(value = "classpath:sql/user-data.sql", executionPhase = BEFORE_TEST_METHOD)
    })
    void should_not_create_one_user_empty_fields() throws Exception {

        final File jsonFile = new ClassPathResource("json/new_user_empty.json").getFile();
        final String userToCreate = Files.readString(jsonFile.toPath());

        this.mockMvc.perform(post("/registration/user")
                        .contentType(APPLICATION_JSON)
                        .content(userToCreate))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @SqlGroup({
            @Sql(value = "classpath:sql/reset.sql", executionPhase = BEFORE_TEST_METHOD),
            @Sql(value = "classpath:sql/user-data.sql", executionPhase = BEFORE_TEST_METHOD)
    })
    void should_not_create_one_user_empty_field_name() throws Exception {

        final File jsonFile = new ClassPathResource("json/new_user_empty_name.json").getFile();
        final String userToCreate = Files.readString(jsonFile.toPath());

        this.mockMvc.perform(post("/registration/user")
                        .contentType(APPLICATION_JSON)
                        .content(userToCreate))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @SqlGroup({
            @Sql(value = "classpath:sql/reset.sql", executionPhase = BEFORE_TEST_METHOD),
            @Sql(value = "classpath:sql/user-data.sql", executionPhase = BEFORE_TEST_METHOD)
    })
    void should_not_create_one_user_empty_field_email() throws Exception {

        final File jsonFile = new ClassPathResource("json/new_user_empty_email.json").getFile();
        final String userToCreate = Files.readString(jsonFile.toPath());

        this.mockMvc.perform(post("/registration/user")
                        .contentType(APPLICATION_JSON)
                        .content(userToCreate))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @SqlGroup({
            @Sql(value = "classpath:sql/reset.sql", executionPhase = BEFORE_TEST_METHOD),
            @Sql(value = "classpath:sql/user-data.sql", executionPhase = BEFORE_TEST_METHOD)
    })
    void should_not_create_one_user_empty_field_password() throws Exception {

        final File jsonFile = new ClassPathResource("json/new_user_empty_password.json").getFile();
        final String userToCreate = Files.readString(jsonFile.toPath());

        this.mockMvc.perform(post("/registration/user")
                        .contentType(APPLICATION_JSON)
                        .content(userToCreate))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @SqlGroup({
            @Sql(value = "classpath:sql/reset.sql", executionPhase = BEFORE_TEST_METHOD),
            @Sql(value = "classpath:sql/user-data.sql", executionPhase = BEFORE_TEST_METHOD)
    })
    public void list_users_thenStatus200_pagination() throws Exception {

        final File jsonFile = new ClassPathResource("json/users_list_page1.json").getFile();
        final String page1ExpectedJson = Files.readString(jsonFile.toPath());
        final String page2ExpectedJson = Files.readString(new ClassPathResource("json/users_list_page2.json").getFile().toPath());

        this.mockMvc.perform(get("/registration/list")
                        .param("page", "0")
                        .param("size", "3")
                        .param("sort", "userId")
                        .contentType(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(page1ExpectedJson));

        this.mockMvc.perform(get("/registration/list")
                        .param("page", "1")
                        .param("size", "3")
                        .param("sort", "userId")
                        .contentType(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(page2ExpectedJson));
    }

    @Test
    @SqlGroup({
            @Sql(value = "classpath:sql/reset.sql", executionPhase = BEFORE_TEST_METHOD),
            @Sql(value = "classpath:sql/user-data.sql", executionPhase = BEFORE_TEST_METHOD)
    })
    void should_delete_one_user_by_id() throws Exception {

        this.mockMvc.perform(MockMvcRequestBuilders.delete("/registration/{id}", 3)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        this.mockMvc.perform(get("/customers/{id}", 3))
                .andExpect(status().isNotFound());
    }

    @Test
    @SqlGroup({
            @Sql(value = "classpath:sql/reset.sql", executionPhase = BEFORE_TEST_METHOD),
            @Sql(value = "classpath:sql/user-data.sql", executionPhase = BEFORE_TEST_METHOD)
    })
    void should_update_one_user_by_id() throws Exception {

        final File jsonFile = new ClassPathResource("json/update_user.json").getFile();
        final String userToUpdate = Files.readString(jsonFile.toPath());

        this.mockMvc.perform(MockMvcRequestBuilders.get("/registration/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Denis"))
                .andExpect(jsonPath("$.email").value("denis@tut.by"))
                .andExpect(jsonPath("$.password").value("mypassword"))
                .andExpect(jsonPath("$.id").value("1"));

        this.mockMvc.perform(MockMvcRequestBuilders.put("/registration/{id}", 1)
                        .contentType(APPLICATION_JSON)
                        .content(userToUpdate))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Denis_pet"))
                .andExpect(jsonPath("$.email").value("denis@tut.by"))
                .andExpect(jsonPath("$.password").value("mypassword2"));
    }

    @Test
    @SqlGroup({
            @Sql(value = "classpath:sql/reset.sql", executionPhase = BEFORE_TEST_METHOD),
            @Sql(value = "classpath:sql/user-data.sql", executionPhase = BEFORE_TEST_METHOD)
    })
    void should_not_update_one_user_by_id_empty_fields() throws Exception {

        final File jsonFile = new ClassPathResource("json/update_user_empty.json").getFile();
        final String userToUpdate = Files.readString(jsonFile.toPath());

        this.mockMvc.perform(MockMvcRequestBuilders.get("/registration/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Denis"))
                .andExpect(jsonPath("$.email").value("denis@tut.by"))
                .andExpect(jsonPath("$.password").value("mypassword"))
                .andExpect(jsonPath("$.id").value("1"));

        this.mockMvc.perform(MockMvcRequestBuilders.put("/registration/{id}", 1)
                        .contentType(APPLICATION_JSON)
                        .content(userToUpdate))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @SqlGroup({
            @Sql(value = "classpath:sql/reset.sql", executionPhase = BEFORE_TEST_METHOD),
            @Sql(value = "classpath:sql/user-data.sql", executionPhase = BEFORE_TEST_METHOD)
    })
    void should_not_update_one_customer_by_id_empty_field_name() throws Exception {

        final File jsonFile = new ClassPathResource("json/update_user_empty_name.json").getFile();
        final String userToUpdate = Files.readString(jsonFile.toPath());

        this.mockMvc.perform(MockMvcRequestBuilders.get("/registration/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Denis"))
                .andExpect(jsonPath("$.email").value("denis@tut.by"))
                .andExpect(jsonPath("$.password").value("mypassword"))
                .andExpect(jsonPath("$.id").value("1"));

        this.mockMvc.perform(MockMvcRequestBuilders.put("/registration/{id}", 1)
                        .contentType(APPLICATION_JSON)
                        .content(userToUpdate))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }


    @Test
    @SqlGroup({
            @Sql(value = "classpath:sql/reset.sql", executionPhase = BEFORE_TEST_METHOD),
            @Sql(value = "classpath:sql/user-data.sql", executionPhase = BEFORE_TEST_METHOD)
    })
    void should_not_update_one_customer_by_id_empty_field_email() throws Exception {

        final File jsonFile = new ClassPathResource("json/update_user_empty_email.json").getFile();
        final String userToUpdate = Files.readString(jsonFile.toPath());

        this.mockMvc.perform(MockMvcRequestBuilders.get("/registration/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Denis"))
                .andExpect(jsonPath("$.email").value("denis@tut.by"))
                .andExpect(jsonPath("$.password").value("mypassword"))
                .andExpect(jsonPath("$.id").value("1"));

        this.mockMvc.perform(MockMvcRequestBuilders.put("/registration/{id}", 1)
                        .contentType(APPLICATION_JSON)
                        .content(userToUpdate))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @SqlGroup({
            @Sql(value = "classpath:sql/reset.sql", executionPhase = BEFORE_TEST_METHOD),
            @Sql(value = "classpath:sql/user-data.sql", executionPhase = BEFORE_TEST_METHOD)
    })
    void should_not_update_one_customer_by_id_empty_field_password() throws Exception {

        final File jsonFile = new ClassPathResource("json/update_user_empty_password.json").getFile();
        final String userToUpdate = Files.readString(jsonFile.toPath());

        this.mockMvc.perform(MockMvcRequestBuilders.get("/registration/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Denis"))
                .andExpect(jsonPath("$.email").value("denis@tut.by"))
                .andExpect(jsonPath("$.password").value("mypassword"))
                .andExpect(jsonPath("$.id").value("1"));

        this.mockMvc.perform(MockMvcRequestBuilders.put("/registration/{id}", 1)
                        .contentType(APPLICATION_JSON)
                        .content(userToUpdate))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @SqlGroup({
            @Sql(value = "classpath:sql/reset_p.sql", executionPhase = BEFORE_TEST_METHOD),
            @Sql(value = "classpath:sql/user-data_p.sql", executionPhase = BEFORE_TEST_METHOD)
    })
    void should_create_one_product() throws Exception {
        final File jsonFile = new ClassPathResource("json/new_product.json").getFile();
        final String userToCreate = Files.readString(jsonFile.toPath());

        ResultActions resultActions = this.mockMvc.perform(post("/products/product")
                        .contentType(APPLICATION_JSON)
                        .content(userToCreate))
                .andDo(print());

        Long newId = Long.parseLong(resultActions.andReturn().getResponse().getContentAsString());

        this.mockMvc.perform(MockMvcRequestBuilders.get("/products/{id}", newId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("cucumber"))
                .andExpect(jsonPath("$.category").value("Vegetables"))
                .andExpect(jsonPath("$.id").value(newId));
    }


    @Test
    @SqlGroup({
            @Sql(value = "classpath:sql/reset_p.sql", executionPhase = BEFORE_TEST_METHOD),
            @Sql(value = "classpath:sql/user-data_p.sql", executionPhase = BEFORE_TEST_METHOD)
    })
    void should_not_create_one_product_empty_fields() throws Exception {

        final File jsonFile = new ClassPathResource("json/new_product_empty.json").getFile();
        final String userToCreate = Files.readString(jsonFile.toPath());

        this.mockMvc.perform(post("/products/product")
                        .contentType(APPLICATION_JSON)
                        .content(userToCreate))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @SqlGroup({
            @Sql(value = "classpath:sql/reset_p.sql", executionPhase = BEFORE_TEST_METHOD),
            @Sql(value = "classpath:sql/user-data_p.sql", executionPhase = BEFORE_TEST_METHOD)
    })
    void should_not_create_one_product_empty_field_name() throws Exception {

        final File jsonFile = new ClassPathResource("json/new_product_empty_name.json").getFile();
        final String userToCreate = Files.readString(jsonFile.toPath());

        this.mockMvc.perform(post("/products/product")
                        .contentType(APPLICATION_JSON)
                        .content(userToCreate))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @SqlGroup({
            @Sql(value = "classpath:sql/reset_p.sql", executionPhase = BEFORE_TEST_METHOD),
            @Sql(value = "classpath:sql/user-data_p.sql", executionPhase = BEFORE_TEST_METHOD)
    })
    void should_not_create_one_product_empty_field_category() throws Exception {

        final File jsonFile = new ClassPathResource("json/new_product_empty_category.json").getFile();
        final String userToCreate = Files.readString(jsonFile.toPath());

        this.mockMvc.perform(post("/products/product")
                        .contentType(APPLICATION_JSON)
                        .content(userToCreate))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @SqlGroup({
            @Sql(value = "classpath:sql/reset_p.sql", executionPhase = BEFORE_TEST_METHOD),
            @Sql(value = "classpath:sql/user-data_p.sql", executionPhase = BEFORE_TEST_METHOD)
    })
    public void list_products_thenStatus200_pagination() throws Exception {

        final File jsonFile = new ClassPathResource("json/products_list_page1.json").getFile();
        final String page1ExpectedJson = Files.readString(jsonFile.toPath());
        final String page2ExpectedJson = Files.readString(new ClassPathResource("json/products_list_page2.json").getFile().toPath());

        this.mockMvc.perform(get("/products/list")
                        .param("page", "0")
                        .param("size", "3")
                        .param("sort", "productId")
                        .contentType(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(page1ExpectedJson));

        this.mockMvc.perform(get("/products/list")
                        .param("page", "1")
                        .param("size", "3")
                        .param("sort", "productId")
                        .contentType(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(page2ExpectedJson));
    }

    @Test
    @SqlGroup({
            @Sql(value = "classpath:sql/reset_p.sql", executionPhase = BEFORE_TEST_METHOD),
            @Sql(value = "classpath:sql/user-data_p.sql", executionPhase = BEFORE_TEST_METHOD)
    })
    void should_delete_one_product_by_id() throws Exception {

        this.mockMvc.perform(MockMvcRequestBuilders.delete("/products/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        this.mockMvc.perform(get("/products/{id}", 1))
                .andExpect(status().isNotFound());
    }

    @Test
    @SqlGroup({
            @Sql(value = "classpath:sql/reset_p.sql", executionPhase = BEFORE_TEST_METHOD),
            @Sql(value = "classpath:sql/user-data_p.sql", executionPhase = BEFORE_TEST_METHOD)
    })
    void should_update_one_product_by_id() throws Exception {

        final File jsonFile = new ClassPathResource("json/update_product.json").getFile();
        final String userToUpdate = Files.readString(jsonFile.toPath());

        this.mockMvc.perform(MockMvcRequestBuilders.get("/products/{id}", 3)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("apple"))
                .andExpect(jsonPath("$.category").value("Fruits"))
                .andExpect(jsonPath("$.id").value("3"));

        this.mockMvc.perform(MockMvcRequestBuilders.put("/products/{id}", 3)
                        .contentType(APPLICATION_JSON)
                        .content(userToUpdate))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("apple"))
                .andExpect(jsonPath("$.category").value("Vegetables"));
    }

    @Test
    @SqlGroup({
            @Sql(value = "classpath:sql/reset_p.sql", executionPhase = BEFORE_TEST_METHOD),
            @Sql(value = "classpath:sql/user-data_p.sql", executionPhase = BEFORE_TEST_METHOD)
    })
    void should_not_update_one_product_by_id_empty_fields() throws Exception {

        final File jsonFile = new ClassPathResource("json/update_product_empty.json").getFile();
        final String userToUpdate = Files.readString(jsonFile.toPath());

        this.mockMvc.perform(MockMvcRequestBuilders.get("/products/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("milk"))
                .andExpect(jsonPath("$.category").value("Dairy foods"))
                .andExpect(jsonPath("$.id").value("1"));

        this.mockMvc.perform(MockMvcRequestBuilders.put("/products/{id}", 1)
                        .contentType(APPLICATION_JSON)
                        .content(userToUpdate))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @SqlGroup({
            @Sql(value = "classpath:sql/reset_p.sql", executionPhase = BEFORE_TEST_METHOD),
            @Sql(value = "classpath:sql/user-data_p.sql", executionPhase = BEFORE_TEST_METHOD)
    })
    void should_not_update_one_product_by_id_empty_field_name() throws Exception {

        final File jsonFile = new ClassPathResource("json/update_product_empty_name.json").getFile();
        final String userToUpdate = Files.readString(jsonFile.toPath());

        this.mockMvc.perform(MockMvcRequestBuilders.get("/products/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("milk"))
                .andExpect(jsonPath("$.category").value("Dairy foods"))
                .andExpect(jsonPath("$.id").value("1"));

        this.mockMvc.perform(MockMvcRequestBuilders.put("/products/{id}", 1)
                        .contentType(APPLICATION_JSON)
                        .content(userToUpdate))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @SqlGroup({
            @Sql(value = "classpath:sql/reset_p.sql", executionPhase = BEFORE_TEST_METHOD),
            @Sql(value = "classpath:sql/user-data_p.sql", executionPhase = BEFORE_TEST_METHOD)
    })
    void should_not_update_one_product_by_id_empty_field_category() throws Exception {

        final File jsonFile = new ClassPathResource("json/update_product_empty_category.json").getFile();
        final String userToUpdate = Files.readString(jsonFile.toPath());

        this.mockMvc.perform(MockMvcRequestBuilders.get("/products/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("milk"))
                .andExpect(jsonPath("$.category").value("Dairy foods"))
                .andExpect(jsonPath("$.id").value("1"));

        this.mockMvc.perform(MockMvcRequestBuilders.put("/products/{id}", 1)
                        .contentType(APPLICATION_JSON)
                        .content(userToUpdate))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }


}
