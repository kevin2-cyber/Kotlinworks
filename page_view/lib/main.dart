import 'package:flutter/material.dart';
import 'package:onboarding_screen/onboarding_screen.dart';

void main() => runApp(const PageViewApp());

class PageViewApp extends StatelessWidget {
  const PageViewApp({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: MyHomePage(),
    );
  }
}

class MyHomePage extends StatelessWidget {
  final List<SliderModel> mySlides = [
    SliderModel(
      imageAssetPath: Image.asset(
        'assets/images/main_logo.png',
        scale: 1,
      ),
      title: 'Developer Student Club',
      desc: 'discover people',
      minTitleFontSize: 10,
      descStyle: const TextStyle(
        fontSize: 12,
        fontWeight: FontWeight.w400,
        color: Colors.black,
      ),
      titleStyle: const TextStyle(
        fontSize: 20,
        fontWeight: FontWeight.w400,
        color: Colors.black,
      ),
    ),
    SliderModel(
      imageAssetPath: Image.asset('assets/images/img.jpg'),
      title: 'Developer Student Club',
      desc: 'discover people',
    ),
    SliderModel(
      imageAssetPath: Image.asset('assets/images/img/jpg'),
      title: 'Developer Student Club',
      desc: 'discover people',
    ),
    SliderModel(
      imageAssetPath: Image.asset('assets/images/img.jpg'),
      title: 'Developer Student Club',
      desc: 'discover people',
    ),
    SliderModel(
      imageAssetPath: Image.asset('assets/images/img.jpg'),
      title: 'Developer Student Club',
      desc: 'discover people',
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
    this.descStyle,
    this.titleStyle,
  });

  final Image imageAssetPath;
  final String title;
  final TextStyle? titleStyle;
  final double minTitleFontSize;
  final String desc;
  final TextStyle? descStyle;
  final double miniDescFontSize;
}


