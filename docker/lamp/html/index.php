<html>
<head>
<title>Listing Some $_Server Elements</title>
</head>
<body>
<?php
$envs = array( "HTTP_REFERER", "HTTP_USER_AGENT", "REMOTE_ADDR","REMOTE_HOST", "QUERY_STRING", "PATH_INFO" );
foreach ( $envs as $env )
  print "$env: $_SERVER[$env]<br>";
?>
</body>
</html>