package com.learning.springboot.beans.ae;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.learning.springboot.type.TransactionType;

public class ContraTransactionAE {

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date;

	private BigDecimal amount;

	private String detail;

	private TransactionType type;

	private int fromAccountId;
	
	private int toAccountId;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public TransactionType getType() {
		return type;
	}

	public void setType(TransactionType type) {
		this.type = type;
	}

	public int getFromAccountId() {
		return fromAccountId;
	}

	public void setFromAccountId(int fromAccountId) {
		this.fromAccountId = fromAccountId;
	}

	public int getToAccountId() {
		return toAccountId;
	}

	public void setToAccountId(int toAccountId) {
		this.toAccountId = toAccountId;
	}

	@Override
	public String toString() {
		return "ContraTransactionAE [date=" + date + ", amount=" + amount + ", detail=" + detail + ", type=" + type
				+ ", fromAccountId=" + fromAccountId + ", toAccountId=" + toAccountId + "]";
	}
	
}
