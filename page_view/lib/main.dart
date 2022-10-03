import 'package:flutter/material.dart';
import 'package:google_fonts/google_fonts.dart';
import 'package:onboarding_screen/onboarding_screen.dart';

void main() => runApp(const PageViewApp());

class PageViewApp extends StatelessWidget {
  const PageViewApp({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Onboarding Demo',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: MyHomePage(),
    );
  }
}

class MyHomePage extends StatelessWidget {

  static var descStyle = GoogleFonts.poppins(
    fontSize: 12,
    fontWeight: FontWeight.w400,
    color: Colors.black38,
  );

  static var titleStyle = GoogleFonts.poppins(
    fontSize: 20,
    fontWeight: FontWeight.w400,
    color: Colors.black38,
  );

  final List<SliderModel> mySlides = [
    SliderModel(
      imageAssetPath: Image.asset(
        'assets/images/img.jpg',
        scale: 1,
      ),
      title: 'Developer Student Club',
      desc: 'discover people',
      minTitleFontSize: 10,
      descStyle: descStyle,
      titleStyle: titleStyle,
    ),
    SliderModel(
      imageAssetPath: Image.asset('assets/images/img.jpg'),
      title: 'Developer Student Club',
      desc: 'discover people',
      descStyle: descStyle,
      titleStyle: titleStyle,
    ),
    SliderModel(
      imageAssetPath: Image.asset('assets/images/img.jpg'),
      title: 'Developer Student Club',
      desc: 'discover people',
      descStyle: descStyle,
      titleStyle: titleStyle,
    ),
    SliderModel(
      imageAssetPath: Image.asset('assets/images/img.jpg'),
      title: 'Developer Student Club',
      desc: 'discover people',
      descStyle: descStyle,
      titleStyle: titleStyle,
    ),
    SliderModel(
      imageAssetPath: Image.asset('assets/images/img.jpg'),
      title: 'Developer Student Club',
      desc: 'discover people',
      descStyle: descStyle,
      titleStyle: titleStyle,
    ),
  ];
  final PageController _controller = PageController();

  MyHomePage({super.key});

  @override
  Widget build(BuildContext context) {
    return OnBoardingScreen(
      label: const Text('Get Started'),

      /// This function works when you will complete `OnBoarding`
      function: () {
        print('Navigation');
      },

      /// This [mySlides] must not be more than 5.
      mySlides: mySlides,
      controller: _controller,
      slideIndex: 0,
      statusBarColor: Colors.red,
      startGradientColor: Colors.red,
      endGradientColor: Colors.blue,
      skipStyle: const TextStyle(color: Colors.white),
      pageIndicatorColorList: const [
        Colors.white,
        Colors.green,
        Colors.red,
        Colors.yellow,
        Colors.white
      ],
    );
  }
}

class SliderModel {
  const SliderModel({
    required this.imageAssetPath,
    this.title = "title",
    this.desc = "title",
    this.miniDescFontSize = 12.0,
    this.minTitleFontSize = 15.0,
    required this.descStyle,
    required this.titleStyle,
  });

  final Image imageAssetPath;
  final String title;
  final TextStyle titleStyle;
  final double minTitleFontSize;
  final String desc;
  final TextStyle descStyle;
  final double miniDescFontSize;
}


