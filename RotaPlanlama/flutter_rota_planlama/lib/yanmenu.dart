import 'dart:ffi';
import 'package:flutter/material.dart';
import 'package:flutter_rota_planlama/admin/durak_ekle.dart';
import 'package:flutter_rota_planlama/admin/servis_ekle.dart';
import 'package:flutter_rota_planlama/admin/user_kayit.dart';
import 'package:flutter_rota_planlama/user/bilet_al.dart';
import 'dart:convert';
import 'package:http/http.dart' as http;

class YanMenu extends StatelessWidget {
  final bool admin;
  late final String id;
  YanMenu(this.id, this.admin);

  var count = 0;
  final name = "yunus karaman";
  final email = "yunus@gmail.com";
  final urlImage = "https://dummyimage.com/640x360/fff/aaa";

  final padding = EdgeInsets.symmetric(horizontal: 20);
  @override
  Widget build(BuildContext context) {
    print(id);
    return Drawer(
      child: Container(
        color: Color(0xFF151026),
        padding: EdgeInsets.only(top: 1),
        child: Column(
          mainAxisAlignment: MainAxisAlignment.start,
          children: <Widget>[
            Expanded(
              child: ListView(
                children: <Widget>[
                  buildHeader(
                      urlImage: urlImage,
                      name: name,
                      email: email,
                      onClicked: () => Navigator.pop(context)),
                  buildMenuItem(
                    text: "Anasayfa",
                    icon: Icons.home,
                    onClicked: () => Navigator.pop(context),
                  ),
                  if (admin != true)
                    buildMenuItem(
                        text: "Bilet Al",
                        icon: Icons.airplane_ticket,
                        onClicked: () => Navigator.push(
                            context,
                            MaterialPageRoute(
                                builder: (context) => BiletAl(id, admin)))),
                  const Divider(
                    height: 1.0,
                    color: Colors.blueGrey,
                  ),
                  if (admin == true)
                    ExpansionTile(
                      title: const Text(
                        'Admin paneli',
                        style: TextStyle(color: Colors.white),
                      ),
                      leading: const Icon(
                        Icons.admin_panel_settings,
                        color: Colors.white,
                      ),
                      children: <Widget>[
                        Container(
                          margin: EdgeInsets.only(left: 15),
                          child: buildMenuItem(
                            text: "Kullanıcı Kayıt",
                            icon: Icons.account_box,
                            onClicked: () => Navigator.push(
                                context,
                                MaterialPageRoute(
                                    builder: (context) =>
                                        myRegister(id, admin))),
                          ),
                        ),
                        Container(
                          margin: EdgeInsets.only(left: 15),
                          child: buildMenuItem(
                              text: "Durak Ekle",
                              icon: Icons.stop_circle_outlined,
                              onClicked: () => Navigator.push(
                                  context,
                                  MaterialPageRoute(
                                      builder: (context) =>
                                          stationadd(id, admin)))),
                        ),
                        Container(
                          margin: EdgeInsets.only(left: 15),
                          child: buildMenuItem(
                            text: "Servis Ekle",
                            icon: Icons.bus_alert_sharp,
                            onClicked: () => Navigator.push(
                                context,
                                MaterialPageRoute(
                                    builder: (context) => aracadd(id, admin))),
                          ),
                        ),
                      ],
                    ),
                  const Divider(
                    height: 1.0,
                    color: Colors.blueGrey,
                  ),
                  Container(
                    child: buildMenuItem(
                      text: "Çıkış Yap",
                      icon: Icons.logout,
                      onClicked: () =>
                          Navigator.pushNamed(context, "/user/login"),
                    ),
                  ),
                ],
              ),
            )
          ],
        ),
      ),
    );
  }

  Widget buildHeader({
    required String urlImage,
    required String name,
    required String email,
    VoidCallback? onClicked,
  }) =>
      InkWell(
        onTap: onClicked,
        child: Container(
          padding: padding.add(EdgeInsets.symmetric(vertical: 40)),
          child: Row(
            children: [
              CircleAvatar(radius: 30, backgroundImage: NetworkImage(urlImage)),
              SizedBox(width: 20),
              Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Text(name,
                      style: TextStyle(fontSize: 20, color: Colors.white)),
                  const SizedBox(
                    height: 4,
                  ),
                  Text(
                    email,
                    style: TextStyle(fontSize: 14, color: Colors.white),
                  ),
                ],
              )
            ],
          ),
        ),
      );

  Widget buildMenuItem({
    required String text,
    required IconData icon,
    VoidCallback? onClicked,
    VoidCallback? onClicked1,
  }) {
    final color = Colors.white;
    final hoveColor = Colors.white70;
    return ListTile(
      leading: Icon(icon, color: color),
      title: Text(text, style: TextStyle(color: color)),
      hoverColor: hoveColor,
      onTap: onClicked,
    );
  }
}
