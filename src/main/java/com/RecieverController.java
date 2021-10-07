package com;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

//123456789012345
@RestController
public class RecieverController {
	@GetMapping("/{id}")
	public Reciever validate(@PathVariable("id") final String id, HttpServletResponse response) {
		Long num = Long.parseLong(id);
		long n = num;
		int parity = 1;
		int sum = 0;
		while (num > 0) {
			int d = (int) (num % 10);
			num /= 10;
			if (parity == 0) {
				d <<= 1;
			}
			parity ^= 1;
			while (d > 0) {
				sum += d % 10;
				d /= 10;
			}
		}
		boolean already = (sum % 10 == 0);
		if(!already) {
			int d = (int) (n % 10);
			sum -= d;
			d = 10 - (sum % 10);
			n = (n / 10) * 10 + d;
			
		}
		response.addHeader("Access-Control-Allow-Origin", "*");
		return new Reciever(already, n);

	}

	@GetMapping("/")
	public String welcome() {
		return "Working Properly!";
	}
}
