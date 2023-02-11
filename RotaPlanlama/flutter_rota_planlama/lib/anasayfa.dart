import 'package:flutter/material.dart';
import './yanmenu.dart';

class AnaSayfa extends StatefulWidget {
  //const AnaSayfa({Key? key}) : super(key: key);
  var id;
  var admin;
  AnaSayfa(this.id, this.admin);
  @override
  _AnaSayfaState createState() => _AnaSayfaState();
}

class _AnaSayfaState extends State<AnaSayfa> {
  //GlobalKey<ScaffoldState> _scaffold = GlobalKey<ScaffoldState>();

  @override
  Widget build(BuildContext contex) {
    print(widget.id);
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Color(0xFF151026),
        title: const Text("AnaSayfa",
            style: TextStyle(fontWeight: FontWeight.bold)),
        centerTitle: true,
      ),
      body: Container(),
      endDrawer: YanMenu(widget.id, widget.admin),
    );
  }
}
