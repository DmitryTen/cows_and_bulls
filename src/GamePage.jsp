<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<html>
    <head>
        <title>Игра "Быки и Коровы"</title>
    </head>
 
    <body>
        <form action="<c:url value="/gamePage"/>" method="POST">
		<H1>Логическая игра "Быки и Коровы"</H1>
		<p>Кратие правила игры: <br/> 
		ЦЕЛЬ: Участники (игрок и компьютер) загадывают по четырехзначному числу ХХХХ, задача каждого игрока - отгадать загаданное соперником число. <br/>
		ИГРОВОЙ ПРОЦЕСС: Участники по очереди дают сопернику произвольное четырехзначное число, соперник же отвечает, сколько быков и коров находится в заданном числе.<br/>
		КОРОВА: в числе, которое Вам передали присутствуют цифры из загаданного Вами числа, НО ИХ ПОЗИЦИИ - ОТЛИЧАЮТСЯ. <br/><br/>
		Например: Загадано число "1234", запрошено число "3546". 
		В данном запросе присутствуют 2 коровы: "3" - присутсвует в переданном числе (поз.1) и в загаданном (поз.3) и "4" - присутсвует в переданном числе (поз.3) и в загаданном (поз.4) <br/>
		БЫК: в числе, которое Вам передали присутствуют цифры из загаданного Вами числа И ИХ ПОЗИЦИИ - СОВПАДАЮТ. <br/>
		Например: Загадано число "1234", запрошено число "3276". 
		В данном запросе присутствуют 1 бык и 1 корова: "3" - корова, присутсвует в переданном числе (поз.1) и в загаданном (поз.3) и "2" - бык, присутсвует в переданном числе (поз.2) и в загаданном (поз.2) <br/>
		Путем последовательной передачи чисел сопернику и получения информации о количестве быков и коров, необходимо отгадать загаданное соперником число раньше соперника. <br/>
		PS: Жульничать не стоит, если Вы загадали одно число в начале игры, а потом "передумали", игра завершится как только показания перестанут сходиться.
		</p>	
		
		<table>
			<tr> 
				<th>Игрок: <c:out value="${form.player}"/> </th>
				<th>Игрок: Computer </th>
			</tr>
			<tr>
				<td>Введите Ваше четырехзначное число:<input type="number" name="playerNumber" pattern="[0-9]{4}"/> </br>
					<input type="submit" name="sendInfo" value="Ввести"/></td>
				</td>
				<td>Отметьте, сколько быков и коров в данном числе: <c:out value="${form.PCnumber}"/> <input type="hidden" name="previousStepPCNumber" value='<c:out value="${form.PCnumber}"/>'> <br/>
					<table>
						<td><input type="radio" name="cow" value="0"> Ноль коров <br/>
							<input type="radio" name="cow" value="1"> Одна корова <br/>
							<input type="radio" name="cow" value="2"> Две коровы <br/>
							<input type="radio" name="cow" value="3"> Три коровы <br/>
							<input type="radio" name="cow" value="4"> Четыре коровы
						</td>
						<td><input type="radio" name="bull" value="0"> Ноль быков <br/>
							<input type="radio" name="bull" value="1"> Один бык <br/>
							<input type="radio" name="bull" value="2"> Два быка <br/>
							<input type="radio" name="bull" value="3"> Три быка <br/>
							<input type="radio" name="bull" value="4"> Четыре быка
						</td>
					</table>
				</td>
			</tr>
		</table>
		
		<c:forEach var="numberInfo" items="${form.numberInfoList}">
			<p>Ход№: <c:out value="${numberInfo.step}"/> , Число: <c:out value="${numberInfo.number}"/>, Коров: <c:out value="${numberInfo.cows}"/>, Быков: <c:out value="${numberInfo.bulls}"/> </p>
		</c:forEach>
			
        </form>
    </body>
</html>