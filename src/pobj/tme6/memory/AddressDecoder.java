package pobj.tme6.memory;

import java.util.ArrayList;
import java.util.List;

public class AddressDecoder implements ICopyableMemory {
	
	private List<MemorySlot> list;
	private int size;

	public AddressDecoder(int size) {
		this.list = new ArrayList<>();
		this.size = size;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public int read(int addr) {
		
		int addrLocale;
		
		for (int i = 0 ; i < list.size();i++) {
			int base = list.get(i).getBaseAddress();
			IMemory memo = list.get(i).getMemory();
			
			if(base <= addr && addr < base + memo.size()) {
				addrLocale = addr - base;
				return memo.read(addrLocale);
			}
		}
		throw new IllegalArgumentException();	
	}

	@Override
	public void write(int addr, int value) {
		
int addrLocale;
		
		for (int i = 0 ; i < list.size();i++) {
			int base = list.get(i).getBaseAddress();
			IMemory memo = list.get(i).getMemory();
			
			if(base <= addr && addr < base + memo.size()) {
				addrLocale = addr - base;
				memo.write(addrLocale,value);
				return;
			}
		}
		throw new IllegalArgumentException();
		
	}
	
	public void add(MemorySlot m) {
		list.add(m);
		
	}
	
	public void remove (MemorySlot m) {
		list.remove(m);
		
	}

	@Override
	public ICopyableMemory copy() throws UnsupportedOperationException{
		
		AddressDecoder res = new AddressDecoder(this.size);
		
		for(int i = 0 ; i < list.size(); i++) {
			IMemory memo = list.get(i).getMemory();
			if (memo instanceof ICopyableMemory ) {
				IMemory m = (((ICopyableMemory) memo).copy());
				int addr = list.get(i).getBaseAddress();				
				res.list.add(new MemorySlot(addr,m));
				}else {
					throw new UnsupportedOperationException();
				}
			}
		    return res;
		}
 
		
	}
	

