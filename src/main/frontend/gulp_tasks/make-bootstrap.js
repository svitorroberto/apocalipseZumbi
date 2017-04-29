const gulp = require('gulp');
// For JS
gulp.task('make-bootstrap-js', function(){
  return gulp.src("./node_modules/bootstrap/dist/*")
    .pipe(gulp.dest("src/app/"));
    // It will create `bootstrap.js` in directory `assets`.
});
