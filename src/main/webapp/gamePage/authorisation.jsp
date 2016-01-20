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

		<p>Для старта игры, введите Ваше игровое имя: </p>


		<form action="/cows_and_bulls/gamePage" method="get">
			<input type="text" name="login"/> - Login <br>
			<input type="submit" value="Start game">
		</form>

    </body>
</html>