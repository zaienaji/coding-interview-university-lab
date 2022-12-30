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

	@Test
	void recursiveTest() {
		
		int base = 2;
        int exponent = 7;
        
        int raise = raise(base, exponent);
        
        assertThat(raise).isEqualTo(128);

	}

	private int raise(int base, int exponent) {

		if (exponent < 4)
			return raise(base, exponent, 1);

		int half = raise(base, exponent / 2);
		int full = half * half;

		if (exponent % 2 == 0)
			return full;

		return full * base;
	}

	private int raise(int base, int exponent, int accumulation) {

		if (exponent == 1)
			return accumulation * base;

		return raise(base, exponent - 1, accumulation * base);
	}

}
