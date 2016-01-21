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
		<H4>PS: Обращаю внимание, что если количество быков или коров будет введено неверно хотя бы один раз, Ваше число будет отгадано неверно или не будет отгадано вовсе. <br/>
        	Поэтому будьте внимательны. Если Вы выиграли, проверьте все Ваши предыдущие ходы на правильность. Алгоритму требутеся не более 12(максимум, в среднем - 8..10) ходов, чтобы разгадать любое загаданное Вами число</H4>

		<form action="<c:url value="/gamePage"/>" method="POST">
			Начать игру заново: <input type="submit" name="newGame" value="New Game"/>
		</form>

		<H3>Game started!</H3>
		<H4>История ходов:</H4>

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
        <br/>
		<br/>
        <H4>And the Winner is... <c:out value="${pageView.winner}"/></H4>
        <p>Число загаданное <c:out value="${pageView.playerName}"/>: <c:out value="${pageView.playersNumber}"/></p>
        <p>Число загаданное Computer: <c:out value="${pageView.computersNumber}"/>:</p>

    </body>
</html>