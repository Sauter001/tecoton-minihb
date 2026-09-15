/**
 * minihb. JPQL과 비슷하게 생긴 미니 언어를 SQL로 번역하는 컴파일러.
 *
 * <p>패키지별로 채워 넣을 것(단계 번호는 hibernate-build-v1 기준).
 * <pre>
 *   meta/     EntityMeta, AttributeMeta, Metamodel        단계 2
 *   hql/      Lexer, Token, Parser, SyntaxTree            단계 1
 *   builder/  Q, PathBuilder                              단계 3
 *   derived/  MethodNameParser                            단계 4
 *   qm/       QmSelect, QmFrom, QmPath, QmExpr, QmComparison, QmParam  단계 2
 *   sql/      SqlSelect, SqlTable, SqlJoin, SqlColumn, SqlPredicate    단계 5(둘지 말지는 직접 결정)
 *   render/   Renderer, MySqlRenderer, PostgresRenderer   단계 5
 *   results/  AssemblyPlan, EntityReader, CollectionReader 단계 7
 *   CompiledQuery                                          단계 5에서 만들고 단계 7에서 필드가 는다
 * </pre>
 *
 * <p>규칙.
 * <ul>
 *   <li>접두사는 Qm으로 통일한다. Sqm으로 지으면 하이버네이트 클래스와 헷갈린다.</li>
 *   <li>파서 생성기(ANTLR, JavaCC) 금지. 재귀 하강으로 손으로 쓴다.</li>
 *   <li>중간 표현을 우회하는 경로 금지. 어떤 프론트엔드도 SQL이나 HQL 문자열을 직접 조립하지 않는다.
 *       (단계 6의 BuilderToPostgresDirect만 예외이고, 그것도 만들자마자 지운다)</li>
 *   <li>막히기 전에 하이버네이트 소스를 열지 않는다. 설계 -&gt; 막힘 -&gt; 내 답 -&gt; 소스 순서.</li>
 *   <li>예외 처리는 throw new IllegalStateException(메시지) 한 줄로 끝낸다.</li>
 * </ul>
 */
package com.sauter001.demo.spike.minihb;
