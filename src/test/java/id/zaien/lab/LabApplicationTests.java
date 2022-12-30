package id.zaien.lab;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LabApplicationTests {

	@Autowired
	private HelloController sut;

	@Test
	void contextLoads() {
		assertThat(sut).isNotNull();
	}

}
