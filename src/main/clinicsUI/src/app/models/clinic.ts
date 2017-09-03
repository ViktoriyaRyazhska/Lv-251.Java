export  class Clinic{
  private _id: number;
  private _name: string;
  private _firstPhone: string;
  private _city: string;
  private _address: string;
  private _district_name: string;
  private _photo: string;
  private _latitude: number;
  private _longitude: number;

  get latitude(): number {
    return this._latitude;
  }

  set latitude(value: number) {
    this._latitude = value;
  }

  get longitude(): number {
    return this._longitude;
  }

  set longitude(value: number) {
    this._longitude = value;
  }

  get photo(): string {
    return this._photo;
  }

  set photo(value: string) {
    this._photo = value;
  }


  constructor(id: number, name: string, firstPhone: string, city: string, address: string, district_name: string, photo: string, latitude: number, longitude: number) {
    this._id = id;
    this._name = name;
    this._firstPhone = firstPhone;
    this._city = city;
    this._address = address;
    this._district_name = district_name;
    this._photo = photo;
    this._latitude = latitude;
    this._longitude = longitude;
  }

  get id(): number {
    return this._id;
  }

  set id(value: number) {
    this._id = value;
  }

  get name(): string {
    return this._name;
  }

  set name(value: string) {
    this._name = value;
  }

  get firstPhone(): string {
    return this._firstPhone;
  }

  set firstPhone(value: string) {
    this._firstPhone = value;
  }

  get city(): string {
    return this._city;
  }

  set city(value: string) {
    this._city = value;
  }

  get address(): string {
    return this._address;
  }

  set address(value: string) {
    this._address = value;
  }

  get district_name(): string {
    return this._district_name;
  }

  set district_name(value: string) {
    this._district_name = value;
  }
}

