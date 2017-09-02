import {Clinic} from "../models/Clinic";

export class ClinicsService{
  private _clinics : Clinic[] =
    [
      new Clinic(22, "Pidor", "202", "Pidor", "Pidor", "Pidor",
        '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'),
      new Clinic(22, "Nahui", "202", "Pidor", "Pidor", "Pidor",
        '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')

    ]
  get clinics(): Clinic[] {
    return this._clinics.slice();
  }
}