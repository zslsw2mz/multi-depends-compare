package depends.entity.types;

import depends.entity.ContainerEntity;
import depends.entity.Entity;
import depends.entity.TypeInfer;

public class VarEntity extends Entity {
	private String rawType;
	private TypeEntity type;
	public VarEntity(String simpleName,  String rawType, Entity parent, int id) {
		super(simpleName,  parent,id);
		this.rawType = rawType;
	}

	public String getRawType() {
		return rawType;
	}

	public TypeEntity getType() {
		return type;
	}

	public void setType(TypeEntity type) {
		this.type = type;
	}

	@Override
	public void inferLocalLevelTypes(TypeInfer typeInferer) {
		type = typeInferer.inferType(this, rawType,true);
		if (type==null) {
			if (((ContainerEntity)getParent()).isGenericTypeParameter(rawType)) {
				type = TypeInfer.genericParameterType;
			}
		}
	}

	
}
