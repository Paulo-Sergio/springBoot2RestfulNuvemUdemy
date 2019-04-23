package br.com.restful.udemy.exception;

public class UnsuportedMathOperationException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public UnsuportedMathOperationException(String msg) {
		super(msg);
	}
}
