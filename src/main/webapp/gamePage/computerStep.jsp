<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>Игра "Быки и Коровы"</title>
    </head>
 
    <body>
		<H1>Логическая игра "Быки и Коровы"</H1>

		<p>Кратие правила игры: <br/>
			Правила предельно просты: участники, игрок (Вы) и компьютер загадываете по четырехзначному числу ХХХХ (число может быть любым). <br/>
			Задача - отгадать загаданное соперником число, передавая противнику числа-запросы и получая ответы в виде быков и коров. Ходы делаются по очереди.<br/>
			<br/>
			Пример:<br/>
			Соперником загадано число "1234" (Вы его не знаете)<br/>
			Вы передаете ему произвольное число, допустим "3246". Соперник сравнивает свое загаданное число с Вашим произвольно запрошенным и выдает Вам <br/>
			количество быков и коров, где <br/>
			КОРОВА - цифра в Вашем произвольном числе, которая также присутствует и в загаданном числе, НО ее позиция - не совпадает с загаданной.<br/>
			БЫК - цифра в Вашем произвольном числе, которая также присутствует и в загаданном числе И ее позиция - совпадает с загаданной.<br/>
			В нашем случае, для числа 3246 ответом будет: 2 коровы (это цифры 3 и 4), 1 бык (цифра 2).<br/>
			<br/>
			Приведу еще несколько примеров загаданных чисел, например:<br/>
			загадано число 4333, переданные числа-запросы:<br/>
			1234 - 1 бык(3), 1 корова(4). Цифры в скобках даны для понимания, в игре они не даются.<br/>
			3321 - 1 бык(3), 1 корова(3). Цифры в скобках даны для понимания, в игре они не даются.<br/>
			4333 - 4 быка. Число отгадано.
		</p>
		<H4>Game started!</H4>
		<br/>
		<H5>История ходов:</H5>

		<c:forEach var="numberViewPlayer" items="${pageView.playerList}">
			<p>Ход№: <c:out value="${numberViewPlayer.step}"/>, <c:out value="${numberViewPlayer.playerName}"/>, Запрошенное число:
				<c:out value="${numberViewPlayer.number}"/>, количество коров: <c:out value="${numberViewPlayer.cows}"/>, количество быков: <c:out value="${numberViewPlayer.bulls}"/> </p>
		</c:forEach>
		<br/>
		<br/>
		<c:forEach var="numberViewComputer" items="${pageView.computerList}">
			<p>Ход№: <c:out value="${numberViewComputer.step}"/>, <c:out value="${numberViewComputer.playerName}"/>, Запрошенное число:
				<c:out value="${numberViewComputer.number}"/>, количество коров: <c:out value="${numberViewComputer.cows}"/>, количество быков: <c:out value="${numberViewComputer.bulls}"/> </p>
		</c:forEach>



		<form action="<c:url value="/gamePage"/>" method="POST">
			Отметьте, сколько быков и коров в данном числе: <c:out value="${pageView.stringNumber}"/> <input type="hidden" name="previousStepPCNumber" value='<c:out value="${pageView.stringNumber}"/>'> <br/>
				<table>
					<td><input type="radio" name="cow" value="0" checked> Ноль коров <br/>
						<input type="radio" name="cow" value="1"> Одна корова <br/>
						<input type="radio" name="cow" value="2"> Две коровы <br/>
						<input type="radio" name="cow" value="3"> Три коровы <br/>
						<input type="radio" name="cow" value="4"> Четыре коровы
					</td>
					<td><input type="radio" name="bull" value="0" checked> Ноль быков <br/>
						<input type="radio" name="bull" value="1"> Один бык <br/>
						<input type="radio" name="bull" value="2"> Два быка <br/>
						<input type="radio" name="bull" value="3"> Три быка <br/>
						<input type="radio" name="bull" value="4"> Четыре быка
					</td>
				</table>
			<input type="submit" name="sendInfo" value="Ввести"/>
		</form>

    </body>
</html>