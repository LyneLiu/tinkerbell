import {Component} from "@angular/core";
import {OwnerService} from "../services/owner.service";

@Component({
  selector: 'owner',
  templateUrl: '../templates/owner.component.html',
  providers: [OwnerService]
})

export class OwnerComponent {
  ownerName:string;
  ownerAge:string;
  ownerAddress:string;
  datachange_lasttime:Date;
  owners:Owner[];

  constructor(private _ownerService:OwnerService) {
    this.ownerName = "lyne";
    this.ownerAge = "27";
    this.ownerAddress = "shanghai";
    this.datachange_lasttime = new Date();
    this._ownerService.owner_info().subscribe(res => {
      this.owners = res;
    });
  }

}

interface Owner {
  ownerName:string;
  ownerAge:string;
  ownerAddress:string;
}
