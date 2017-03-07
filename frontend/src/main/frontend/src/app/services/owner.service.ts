/*
 将从网络服务器获取数据，or获取本地静态数据的这些操作抽象为service组件；
 Note：
 1、所有的service组件以Service结尾；
 2、文件名全部是小写，并且多个单词用短线链接-，如：shop-car.service.ts
 3、文件名称尽量与Service相符
 */
import {Injectable} from "@angular/core";
import {Http} from "@angular/http";
import "rxjs/add/operator/map";

@Injectable()
export class OwnerService {

  owner_info_url:string;

  constructor(private http:Http) {
    console.log("OwnerService Initialized......");
    this.owner_info_url = "http://localhost:8088/owner_info"
  }

  owner_info() {
    return this.http.get(this.owner_info_url)
      .map(res => res.json());
  }
}
