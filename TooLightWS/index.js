const express = require('express')

const app = express()
const pug = require('pug')

const bodyParser = require('body-parser')

var missionList = []

app.use( bodyParser.json() );       // to support JSON-encoded bodies
app.use(bodyParser.urlencoded({     // to support URL-encoded bodies
  extended: true
})); 

app.get('/', function (req, res) {
  res.send(pug.renderFile('main.pug'))
})

app.post('/sendToolForm', function(req, res) {
    missionList.push({ "Name" : req.body.Name, "Description" : req.body.Description, "Tools" : req.body.Tools });
    res.redirect('/');
})

app.get('/missionList', function (req,res) {
  res.send(missionList);
})

// Set port
var server = app.listen(process.env.PORT || 8000, function () {
    var port = server.address().port;
    console.log("App now running on port", port);
});