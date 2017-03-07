import {Component} from "@angular/core";
import {OwnerService} from "../services/owner.service";

@Component({
  selector: 'owner',
  templateUrl: '../templates/owner.component.html',
  providers: [OwnerService]
})

export class OwnerComponent {
  owners:Owner[];

  constructor(private _ownerService:OwnerService) {
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
