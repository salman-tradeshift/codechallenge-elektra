'use strict';

module.exports = function(grunt) {

  // Project configuration.
  grunt.initConfig({
    // Metadata.
    pkg: grunt.file.readJSON('package.json'),
    banner: '/*! <%= pkg.name %> - v<%= pkg.version %> - ' +
      '<%= grunt.template.today("yyyy-mm-dd") %>\n' +
      '<%= pkg.homepage ? "* " + pkg.homepage + "\\n" : "" %>' +
      '* Copyright (c) <%= grunt.template.today("yyyy") %> <%= pkg.author.name %>;' +
      ' Licensed <%= _.pluck(pkg.licenses, "type").join(", ") %> */\n',
    // Task configuration.
    src: {
       gruntfile: 'Gruntfile.js',
       lib:       'lib/**/*.js',
       test:      'test/**/*.spec.js'
    } ,
    concat: {
      options: {
        banner: '<%= banner %>',
        stripBanners: true
      },
      build: {
        src: ['lib/<%= pkg.name %>.js'],
        dest: 'public/js/bundle.js'
      }
    },
    browserify: {
        build: {
            src: '<%= concat.build.dest %>',
            dest: '<%= concat.build.dest %>'
        }
    },
    uglify: {
      options: {
        banner: '<%= banner %>'
      },
      build: {
        src: '<%= browserify.build.dest %>',
        dest: 'public/js/bundle.min.js'
      }
    },
    jshint: {
      options: {
        jshintrc: '.jshintrc'
      },
      gruntfile: {
        src: ['<%= src.gruntfile %>']
      },
      lib: {
        src: ['<%= src.lib %>']
      },
      test: {
        src: ['<%= src.test %>']
      }
    },
    watch: {
      gruntfile: {
        files: ['<%= src.gruntfile %>'],
        tasks: ['jshint:gruntfile']
      },
      lib: {
        files: ['<%= src.lib %>'],
        tasks: ['jshint:lib', 'default']
      },
      test: {
        files: ['<%= src.test %>'],
        tasks: ['jshint:test', 'test']
      }
    },
    karma: {
      unit: {
        options: {
          frameworks: ['browserify','jasmine'],
          singleRun: true,
          preprocessors: {
            '<%= src.lib %>' : ['browserify']
          },
          reporters: ['progress'],
          browsers: ['PhantomJS'],
          files: [
            './node_modules/angular/angular.js',
            './node_modules/angular-resource/angular-resource.js',
            './node_modules/angular-mocks/angular-mocks.js',
            './node_modules/socket.io-client/socket.io.js',
            '<%= src.lib %>',
            '<%= src.test %>'
          ],
          plugins: [
              'karma-phantomjs-launcher',
              'karma-jasmine',
              'karma-browserify'
          ],
          colors: true
        }
      }
    }

  });

  grunt.loadNpmTasks('grunt-contrib-concat');
  grunt.loadNpmTasks('grunt-contrib-uglify');
  grunt.loadNpmTasks('grunt-contrib-jshint');
  grunt.loadNpmTasks('grunt-contrib-watch');
  grunt.loadNpmTasks('grunt-browserify');
  grunt.loadNpmTasks('grunt-karma');
  grunt.loadNpmTasks('grunt-contrib-jasmine');

  // Tasks
  grunt.registerTask('test', ['jshint', 'karma']);
  grunt.registerTask('default', ['concat:build', 'browserify:build', 'uglify:build']);

};
