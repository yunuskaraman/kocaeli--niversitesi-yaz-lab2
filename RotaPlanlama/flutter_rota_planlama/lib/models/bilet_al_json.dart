class Durak {
  late String id;
  late String durakAdi;
  late String lat;
  late String lng;

  Durak(this.id, this.durakAdi, this.lat, this.lng);

  Durak.fromJson(Map<String, dynamic> json) {
    id = json['_id'];
    durakAdi = json['durak_adi'];
    lat = json['lat'];
    lng = json['lng'];
  }

  void add(Durak durak) {}
}
