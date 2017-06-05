const express = require('express')

const app = express()
const pug = require('pug')

const bodyParser = require('body-parser')
app.use( bodyParser.json() );       // to support JSON-encoded bodies
app.use(bodyParser.urlencoded({     // to support URL-encoded bodies
  extended: true
})); 

app.get('/', function (req, res) {
  res.send(pug.renderFile('main.pug'))
})

app.post('/sendToolForm', function(req, res) {
    console.log(req.body.Name)
    console.log(req.body.Description)
    console.log(req.body.Tools)
})

app.listen(3000, function () {
  console.log('Example app listening on port 3000!')
})