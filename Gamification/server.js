var express = require('express')
const app = express()
const port = 4200

app.use(express.static(__dirname+ '/dist/Gamification'));

app.listen(port,()=>{
	console.log(`Listening on port ${port}`);
});
