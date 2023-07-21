<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="model1.board.BoardDAO"%>
<%@page import="model1.board.BoardDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
Map<String, Object> param = new HashMap<String, Object>();
String searchField = request.getParameter("searchField");
String searchWord = request.getParameter("searchWord");
if(searchWord != null){
	param.put("searchField", searchField);
	param.put("searchWord", searchWord);
}

BoardDAO dao = new BoardDAO(application);

int totalCount = dao.selectCount(param);
List<BoardDTO> boardLists = dao.selectList(param);
dao.close();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>List.jsp</title>
<script >
function deletePost(num) {
    var confirmed = confirm("정말로 삭제하겠습니까?"); 
    if (confirmed) {
        var form = document.writeFrm;       // 이름(name)이 "writeFrm"인 폼 선택
        form.method = "post";               // 전송 방식 
        form.action = "DeleteProcess.jsp";
        let hNum = document.createElement('input');// 전송 경로
        hNum.setAttribute('type', 'hidden');
        hNum.setAttribute('name', 'num');
        hNum.setAttribute('value', num);
        form.appendChild(hNum);
        form.submit();                      // 폼값 전송
    }
}
</script>
</head>
<body>
<jsp:include page="../common/Link.jsp" />
<h2>목록 보기(List)</h2>
 <!-- 검색폼 --> 
    <form method="get">  
    <table border="1" width="90%">
    <tr>
        <td align="center">
            <select name="searchField"> 
                <option value="title">제목</option> 
                <option value="content">내용</option>
            </select>
            <input type="text" name="searchWord" />
            <input type="submit" value="검색하기" />
        </td>
    </tr>   
    </table>
    </form>
<!-- 게시물 목록 테이블(표) -->

<table border="1" width="90%">
	<!-- 각 컬럼의 이름 -->
	<tr>
		<th width="10%">번호</th>
		<th >제목</th>
		<th width="15%">작성자</th>
		<th width="10%">조회수</th>
		<th width="15%">작성일</th>
		<%
	if(session.getAttribute("UserId") != null 
	&&session.getAttribute("UserId").toString().equals("musthave")){
%>
		<th width="15%">수정</th>
		<th width="15%">삭제</th>
<%} %>
	</tr>
	<!-- 목록의 내용 -->
<%
if(boardLists.isEmpty()){
//게시물이 하나도 없을 때
%>
	<tr>
		<td colspan="5" align="center">
			등록된 게시물이 없습니다.
		</td>
	</tr>
<%
} else{
	//게시물이 있을 때
	int virtualNum = 0;//화면상에서의 게시물 번호
	for(BoardDTO dto: boardLists){ 
		virtualNum = totalCount--;//전체 게시물 수에서 시작해 1씩 감소

%>
	<tr align="center">
		<td><%=virtualNum %></td><!-- 게시물 번호 -->
		<td align="left"> <!-- 제목(+하이퍼링크) -->
			<a href="View.jsp?num=<%=dto.getNum() %>"><%=dto.getTitle() %></a>
		</td>
		<td align="center"><%=dto.getId() %></td> <!-- 작성자 아이디 -->
		<td align="center"><%=dto.getVisitcount() %></td> <!-- 조회수 -->
		<td align="center"><%=dto.getPostdate() %></td> <!-- 작성일 -->
<%
	if(session.getAttribute("UserId") != null 
	&&session.getAttribute("UserId").toString().equals("musthave")){
%>		
		<td > <button type="button" onclick="location.href='Edit.jsp?num=<%=dto.getNum()%>'"> 수정</td>
		<td ><button type="button" onclick="deletePost(<%=dto.getNum()%>)" />삭제 </td>
<%	} %>
	</tr>
<%
	}
}
%>
</table>

<!-- 목록 하단의 [글쓰기]버튼 -->
<table border="1" width="90%">
	<tr align="right">
		<td> <button type="button" onclick="location.href='Write.jsp';">글쓰기</button> </td>
	</tr>
</table>
 	<form name="writeFrm"></form>
</body>
</html>