import 'package:flutter/material.dart';
import 'home.dart';

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
      home: Home(),
    );
  }
}



