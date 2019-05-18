<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="yyf" uri="http://yyf.pager-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>bookStore列表</title>
	<%--导入css --%>
	<link rel="stylesheet" href="${pageContext.request.contextPath }/client/css/main.css" type="text/css" />
</head>

<body class="main">
	<jsp:include page="head.jsp" />
	<jsp:include page="menu_search.jsp" />
	<div id="divpagecontent">
		<table width="100%" border="0" cellspacing="0">
			<tr>
				<td><div style="text-align:right; margin:5px 10px 5px 0px">
						<a href="${pageContext.request.contextPath }/index.jsp">首页</a>&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;&nbsp;
						${category}&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;&nbsp;图书列表
					</div>
					<table cellspacing="0" class="listcontent">
						<tr>
							<td>
								<h1>商品目录</h1>
								<hr />
								<h1>${bean.category}</h1>&nbsp;&nbsp;&nbsp;&nbsp;共${bean.totalCount}种商品
								<hr />
								<div style="margin-top:20px; margin-bottom:5px">
									<img src="${pageContext.request.contextPath }/client/images/productlist.gif" width="100%" height="38" />
								</div>

								<table cellspacing="0" class="booklist">
									<tr>
											<%--<td>--%>
												<%--<div class="divbookpic">--%>
													<%--<p>--%>
														<%--<a href="#">--%>
															<%--<img src="${pageContext.request.contextPath}/bookcover/101.jpg" width="115" height="129" border="0" /> --%>
														<%--</a>--%>
													<%--</p>--%>
												<%--</div>--%>
												<%--<div class="divlisttitle">--%>
													<%--<a href="${pageContext.request.contextPath}/findProductById?id=${p.id}">书名：时空穿行 <br />售价：￥38.8 </a>--%>
												<%--</div>--%>
											<%--</td>--%>
										<c:forEach items="${products}" var="product">
											<td>
												<div class="divbookpic">
													<p>
														<a href="${pageContext.request.contextPath}/client/products/findProductById.do?id=${product.id}">
															<img src="${pageContext.request.contextPath}${product.imgurl}" width="115" height="129" border="0" />
														</a>
													</p>
												</div>
												<div class="divlisttitle">
													<a href="${pageContext.request.contextPath}/client/products/findProductById.do?id=${product.id}">书名： ${product.name}<br />售价：￥${product.price} </a>
												</div>
											</td>

											<%-- <c:if test="${vs.count%4==0}">
											</c:if> --%>
										</c:forEach>
									</tr>
								</table>

							</td>
						</tr>
						<tr>
							<td>
								<div class="pagination">
									<yyf:pager pageIndex="${pageModel.pageIndex}"
											   pageSize="${pageModel.pageSize}"
											   recordCount="${pageModel.recordCount}"
											   style="digg"
											   submitUrl="${pageContext.request.contextPath}/client/products/findProduct_listByCategory.do?pageIndex={0}&category=${category=='全部商品'?null:category}"/>

								</div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</div>
	<jsp:include page="foot.jsp" />
</body>
</html>
