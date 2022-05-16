import { Pipe, PipeTransform } from '@angular/core';
import {FormGroup} from "@angular/forms";

@Pipe({ name: 'appFilter' })
export class SearchFilterPipe implements PipeTransform {



  transform(items: any, searchText: string): any[] {
    if (!items) {
      return [];
    }
    if (!searchText) {
      return items;
    }
    searchText = searchText.toLocaleLowerCase();

    return items.filter(it => {
      return it.name.toString().toLocaleLowerCase().includes(searchText);
    });
  }
}
