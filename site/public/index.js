var express = require('express');
var app = express();
var mysql = require('mysql');

var path = require('path')

// -------------- mysql initialization -------------- //
// USE PARAMETERS FROM DIRECTOR DOCS!!!
var sql_params = {
  connectionLimit : 10,
  user            : process.env.DIRECTOR_DATABASE_USERNAME,
  password        : process.env.DIRECTOR_DATABASE_PASSWORD,
  host            : process.env.DIRECTOR_DATABASE_HOST,
  port            : process.env.DIRECTOR_DATABASE_PORT,
  database        : process.env.DIRECTOR_DATABASE_NAME
}

var pool  = mysql.createPool(sql_params);


// -------------- express 'get' handlers -------------- //

app.get('/', function(req, res){
    res.send('{}')
});

app.get('/upvoteById', function(req, res){
    var qb_id = req.query.id;
    
    // SQL DATABASE TIME!!!
    pool.query('CALL upvote(?);',qb_id, function (error, results, fields) {
      if (error) throw error;

        var result_out = results[0][0]

        //render
        res.json(result_out);   
    });    
});

app.get('/downvoteById', function(req, res){
    var qb_id = req.query.id;
    
    // SQL DATABASE TIME!!!
    pool.query('CALL downvote(?);',qb_id, function (error, results, fields) {
      if (error) throw error;

        var result_out = results[0][0]

        //render
        res.json(result_out);   
    })
});
    

app.get('/getVotesById', function(req, res){
    
    var qb_id = req.query.id;

    // SQL DATABASE TIME!!!
    pool.query('SELECT player_name, upvotes, downvotes FROM ratings WHERE id=?',qb_id, function (error, results, fields) {
      if (error) throw error;

        var result_out = results[0]

        //render
        res.json(result_out);   
    });
})



app.use(express.static(path.join(__dirname,'static')))

// -------------- listener -------------- //
// // The listener is what keeps node 'alive.' 

var listener = app.listen(process.env.PORT || 8080, process.env.HOST || "0.0.0.0", function() {
    console.log("Express server started");
});