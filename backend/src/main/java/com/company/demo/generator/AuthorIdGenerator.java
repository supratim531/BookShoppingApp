package com.company.demo.generator;

import java.io.Serializable;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.Configurable;
import org.hibernate.id.IdentifierGenerator;

@SuppressWarnings("deprecation")
public class AuthorIdGenerator implements IdentifierGenerator, Configurable {

	private static Long ID = 1L;

	private String getAuthorId() {
		final String authorId = "BWAUTH1760" + ID++;
		return authorId;
	}

	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
		return this.getAuthorId();
	}

}
