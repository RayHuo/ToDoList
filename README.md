## 简介
这是一个简单的ToDoList，数据使用SQLite保持在本地。
<br/><br/>

## 注意
<ol>
<li>“返回键”导致程序crash的情况得意修改，不过还有一些小问题</li>
<li>关于EditText的布局还有一些问题，需要调整</li>
</ol>

## 修改思路
<ol>
<li>“返回键”的小问题可能是需要先屏蔽掉“返回键”本身的返回功能，只执行自定义的跳转功能</li>
<li>在当前的自定义“返回键”功能中，如果面临对个activity可以跳到同一个activity，那么可以在跳转时再Bundle中多加一个转入activity的信息，以助确定返回时应该回到哪个activity</li>
</ol>
