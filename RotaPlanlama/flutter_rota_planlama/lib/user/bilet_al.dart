import 'dart:convert';
import 'package:flutter/material.dart';
import 'package:flutter_rota_planlama/models/bilet_al_json.dart';
import 'package:fluttertoast/fluttertoast.dart';
import 'package:http/http.dart' as http;
import 'package:flutter_rota_planlama/anasayfa.dart';

class BiletAl extends StatefulWidget {
  //const myRegister({Key? key}) : super(key: key);

  final String kul_id;
  final bool admin;
  BiletAl(this.kul_id, this.admin);

  @override
  _BiletAlState createState() => _BiletAlState();
}

class _BiletAlState extends State<BiletAl> {
  late String selectedName;
  var userBilet = {};
  List data = [];

  Future biletAl() async {
    var url = 'http://192.168.0.103:8000/user/durak';
    var duraks = <Durak>[];
    try {
      var response = await http.get(Uri.parse(url));
      if (response.statusCode == 200) {
        var dataJson = json.decode(response.body);

        setState(() {
          data = dataJson['data'];
        });

        print(dataJson['data']);

        // Navigator.push(
        //   context, MaterialPageRoute(builder: (context) => AnaSayfa()));
      }
    } catch (e) {
      print(e.toString());
    }
  }

  @override
  void initState() {
    super.initState();
    biletAl();
  }

  var servis_kayit = {};
  final items = ["item1", "item2", "item3"];

  String? value;

  @override
  Widget build(BuildContext context) {
    print("object");
    print(widget.kul_id);
    /* @override
    void initState() {
      biletAl().then((deger) {
        setState(() {
          _duraks.addAll(deger);
          print(deger);
        });
      });
      super.initState();
    }
*/
    return SafeArea(
      child: Container(
        height: 200,
        width: 200,
        //color: Colors.green,
        //padding: EdgeInsets.only(left: 20, bottom: 0, right: 20, top: 40),
        margin: EdgeInsets.all(10),
        decoration: const BoxDecoration(
          image: DecorationImage(
            image: AssetImage(
              'assets/bilet2.png',
            ),
            fit: BoxFit.cover,
          ),
        ),
        child: Scaffold(
          appBar: AppBar(
              title: Text("Bilet AL"),
              elevation: null,
              backgroundColor: Colors.transparent,
              leading: TextButton(
                onPressed: () {
                  Navigator.push(
                      context,
                      MaterialPageRoute(
                          builder: (context) =>
                              AnaSayfa(widget.kul_id, widget.admin)));
                },
                child: const Icon(
                  Icons.arrow_back_ios_rounded,
                  color: Colors.white,
                ),
              )),
          backgroundColor: Colors.transparent,
          body: Stack(
            children: [
              Row(
                mainAxisAlignment: MainAxisAlignment.center,
                children: const [],
              ),
              SingleChildScrollView(
                child: Container(
                  padding: EdgeInsets.only(
                    top: MediaQuery.of(context).size.height * 0.3,
                    //left: 35,
                    //right: 35,
                  ),
                  child: Column(
                    children: [
                      SizedBox(height: 100.0),
                      Container(
                        padding:
                            EdgeInsets.symmetric(horizontal: 12, vertical: 4),
                        decoration: BoxDecoration(
                            borderRadius: BorderRadius.circular(12),
                            border: Border.all(color: Colors.black, width: 4)),
                        child: DropdownButton(
                          value: value,
                          isExpanded: true,
                          hint: Text("duraklar",
                              style: TextStyle(
                                  fontWeight: FontWeight.bold, fontSize: 20)),
                          items: data.map((list) {
                            return DropdownMenuItem(
                              child: Text(
                                list['durak_adi'],
                                style: TextStyle(
                                    fontWeight: FontWeight.bold, fontSize: 20),
                              ),
                              value: list['durak_adi'],
                            );
                          }).toList(),
                          onChanged: (value) => {
                            setState(() => this.value = value.toString()),
                          },
                        ),
                      ),
                      SizedBox(height: 20.0),
                      Row(
                        mainAxisAlignment: MainAxisAlignment.end,
                        children: [
                          ElevatedButton(
                              style: ElevatedButton.styleFrom(
                                maximumSize: const Size(170.0, 90.0),
                                minimumSize: const Size(170.0, 60.0),
                                primary: Colors.black,
                                shape: const StadiumBorder(),
                              ),
                              onPressed: () {
                                if (value == null) {
                                  Fluttertoast.showToast(
                                      msg: "Lütfen bir durak seçiniz",
                                      toastLength: Toast.LENGTH_SHORT,
                                      gravity: ToastGravity.CENTER,
                                      timeInSecForIosWeb: 1,
                                      backgroundColor: Colors.blue.shade400,
                                      textColor: Colors.white,
                                      fontSize: 16.0);
                                } else
                                  biletKayit(value);
                              },
                              child: Row(
                                mainAxisAlignment:
                                    MainAxisAlignment.spaceBetween,
                                //crossAxisAlignment: CrossAxisAlignment.center,
                                children: const [
                                  Text('Bilet Al',
                                      textAlign: TextAlign.center,
                                      style: TextStyle(fontSize: 25)),
                                  Icon(
                                    Icons.airplane_ticket_sharp,
                                    color: Colors.white,
                                  ),
                                ],
                              )),
                        ],
                      ),
                    ],
                  ),
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }
/*
  DropdownMenuItem<String> buildMenuItem(String item) => DropdownMenuItem(
      value: item,
      child: Text(
        item,
        style: TextStyle(fontWeight: FontWeight.bold, fontSize: 20),
      ));
  */

  Future<void> biletKayit(var value) async {
    userBilet = {"durak_adi": value, "kul_id": widget.kul_id};
    var url = 'http://192.168.43.53:8000/user/biletal';

    try {
      var response = await http.post(Uri.parse(url), body: userBilet);

      var jsonData = jsonDecode(response.body);
      print(jsonData);
      if (response.statusCode == 400) {
        Fluttertoast.showToast(
            msg: response.body,
            toastLength: Toast.LENGTH_SHORT,
            gravity: ToastGravity.CENTER,
            timeInSecForIosWeb: 1,
            backgroundColor: Colors.blue.shade400,
            textColor: Colors.white,
            fontSize: 16.0);
      } else {
        if (response.statusCode == 200) {
          Fluttertoast.showToast(
              msg: "Bilet alındı.",
              toastLength: Toast.LENGTH_SHORT,
              gravity: ToastGravity.CENTER,
              timeInSecForIosWeb: 1,
              backgroundColor: Colors.blue.shade400,
              textColor: Colors.white,
              fontSize: 18.0);
          //print(user.admin);
          Navigator.push(
              context,
              MaterialPageRoute(
                  builder: (context) => AnaSayfa(widget.kul_id, widget.admin)));
        }
      }
    } catch (e) {
      print(e.toString());
    }
  }
}
