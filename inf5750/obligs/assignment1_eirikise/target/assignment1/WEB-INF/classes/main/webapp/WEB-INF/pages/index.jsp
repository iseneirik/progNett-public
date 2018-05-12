<html>
<body>

<h2>Leave a message</h2>

<h4>${message}</h4>

<form name="input" action="/send" method="get">
    Message content: <input type="text" name="content">
    <input type="submit" value="Submit">
</form>

<p>
    <a href="/read">
        Get last message
    </a>
</p>

<p>
    <a href="/read/1">
        Get message with id = 1
    </a>
</p>

<p>
    <a href="/hello/Eirik">
        Hello
    </a>
</p>

<p>
    <a href="/welcome/Eirik">
        Welcome
    </a>
</p>

</body>
</html>
