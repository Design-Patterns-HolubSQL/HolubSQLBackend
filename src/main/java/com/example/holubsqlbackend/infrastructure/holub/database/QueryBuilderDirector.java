package com.example.holubsqlbackend.infrastructure.holub.database;

public class QueryBuilderDirector {
	private QueryBuilder queryBuilder;
	
	public void setQueryBuilder(QueryBuilder qb) {
		queryBuilder = qb;
	}
	
	public void customizedTable() {
		queryBuilder.build();
	}
}
